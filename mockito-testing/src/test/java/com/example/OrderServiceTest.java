package com.example;

import com.example.entity.Order;
import com.example.entity.OrderRequest;
import com.example.exception.OrderException;
import com.example.exception.OrderNotFoundException;
import com.example.repository.OrderRepository;
import com.example.service.InventoryService;
import com.example.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new OrderRequest();
        validRequest.setCustomerId("customer-123");
        validRequest.setProductId("product-456");
        validRequest.setQuantity(2);
        validRequest.setTotalAmount(new BigDecimal("199.99"));
        validRequest.setCurrency("USD");
    }

    @Test
    void processOrder_SuccessfulFlow() {
        // Mock inventory check
        when(inventoryService.isAvailable(anyString(), anyInt())).thenReturn(true);
        when(inventoryService.reserve(anyString(), anyInt())).thenReturn(true);

        // Mock payment
        when(paymentGateway.authorizePayment(anyString(), any(BigDecimal.class), anyString()))
                .thenReturn("tx-" + UUID.randomUUID());
        when(paymentGateway.capturePayment(anyString())).thenReturn(true);

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> {
                    Order order = invocation.getArgument(0);
                    if (order.getOrderId() == null) {
                        order.setOrderId("order-" + UUID.randomUUID().toString());
                    }
                    return order;
                });

        Order order = orderService.processOrder(validRequest);
        System.out.println(order);

        assertThat(order)
                .isNotNull()
                .extracting(Order::getOrderId, Order::getTransactionId)
                .isNotNull();

        assertThat(order)
                .isNotNull()
                .extracting(Order::getCustomerId, Order::getProductId, Order::getQuantity, Order::getTotalAmount, Order::getCurrency, Order::getStatus)
                .containsExactlyInAnyOrder("customer-123", "product-456", 2, new BigDecimal("199.99"), "USD", OrderStatus.PROCESSING);

        // Verify interactions
        verify(inventoryService).isAvailable(validRequest.getProductId(), validRequest.getQuantity());
        verify(inventoryService).reserve(validRequest.getProductId(), validRequest.getQuantity());
        verify(paymentGateway).authorizePayment(
                validRequest.getCustomerId(),
                validRequest.getTotalAmount(),
                validRequest.getCurrency()
        );
        verify(paymentGateway).capturePayment(order.getTransactionId());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void processOrder_InventoryUnavailable() {
        when(inventoryService.isAvailable(anyString(), anyInt())).thenReturn(false);

        assertThrows(OrderException.class, () -> {
            orderService.processOrder(validRequest);
        });

        verify(inventoryService).isAvailable(validRequest.getProductId(), validRequest.getQuantity());
        verifyNoInteractions(paymentGateway);
        verifyNoInteractions(orderRepository);
    }

    @Test
    void cancelOrder_Successful() {
        String orderId = "order-123";
        Order existingOrder = new Order();
        existingOrder.setOrderId(orderId);
        existingOrder.setTransactionId("tx-456");
        existingOrder.setProductId("product-456");
        existingOrder.setQuantity(2);
        existingOrder.setTotalAmount(new BigDecimal("100.00"));
        existingOrder.setStatus(OrderStatus.PROCESSING);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(paymentGateway.refundPayment(anyString(), any(BigDecimal.class))).thenReturn(true);
        when(inventoryService.release(anyString(), anyInt())).thenReturn(true);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = orderService.cancelOrder(orderId);

        assertTrue(result);
        verify(orderRepository).findById(orderId);
        verify(paymentGateway).refundPayment(existingOrder.getTransactionId(), existingOrder.getTotalAmount());
        verify(inventoryService).release(existingOrder.getProductId(), existingOrder.getQuantity());
        verify(orderRepository).save(argThat(order -> order.getStatus() == OrderStatus.CANCELLED));
    }

    @Test
    void checkOrderStatus_Found() {
        String orderId = "order-789";
        Order existingOrder = new Order();
        existingOrder.setOrderId(orderId);
        existingOrder.setStatus(OrderStatus.SHIPPED);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        OrderStatus status = orderService.checkOrderStatus(orderId);

        assertEquals(OrderStatus.SHIPPED, status);
        verify(orderRepository).findById(orderId);
    }

    @Test
    void checkOrderStatus_NotFound() {
        String orderId = "non-existent-order";

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.checkOrderStatus(orderId);
        });

        verify(orderRepository).findById(orderId);
    }
}