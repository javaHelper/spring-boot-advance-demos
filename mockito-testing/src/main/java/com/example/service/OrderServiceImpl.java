package com.example.service;

import com.example.OrderStatus;
import com.example.PaymentGateway;
import com.example.entity.Order;
import com.example.entity.OrderRequest;
import com.example.exception.OrderException;
import com.example.exception.OrderNotFoundException;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PaymentGateway paymentGateway;
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order processOrder(OrderRequest request) {
        // 1. Check inventory
        if (!inventoryService.isAvailable(request.getProductId(), request.getQuantity())) {
            throw new OrderException("Product not available in requested quantity");
        }

        // 2. Reserve inventory
        if (!inventoryService.reserve(request.getProductId(), request.getQuantity())) {
            throw new OrderException("Failed to reserve inventory");
        }

        try {
            // 3. Process payment
            String transactionId = paymentGateway.authorizePayment(
                request.getCustomerId(),
                request.getTotalAmount(),
                request.getCurrency()
            );

            if (transactionId == null) {
                throw new OrderException("Payment authorization failed");
            }

            // 4. Capture payment
            boolean paymentCaptured = paymentGateway.capturePayment(transactionId);
            if (!paymentCaptured) {
                paymentGateway.refundPayment(transactionId, request.getTotalAmount());
                throw new OrderException("Payment capture failed");
            }

            // 5. Create order
            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString());
            order.setCustomerId(request.getCustomerId());
            order.setProductId(request.getProductId());
            order.setQuantity(request.getQuantity());
            order.setTotalAmount(request.getTotalAmount());
            order.setCurrency(request.getCurrency());
            order.setTransactionId(transactionId);
            order.setStatus(OrderStatus.PROCESSING);
            order.setCreatedAt(LocalDateTime.now());
            
            return orderRepository.save(order);
        } catch (Exception e) {
            // Release inventory if anything goes wrong
            inventoryService.release(request.getProductId(), request.getQuantity());
            throw e;
        }
    }

    @Override
    public OrderStatus checkOrderStatus(String orderId) {
        return orderRepository.findById(orderId)
                .map(Order::getStatus)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    @Transactional
    public boolean cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if (order.getStatus() == OrderStatus.SHIPPED) {
            throw new OrderException("Cannot cancel shipped order");
        }

        // Release inventory
        inventoryService.release(order.getProductId(), order.getQuantity());

        // Process refund
        boolean refundIssued = paymentGateway.refundPayment(
            order.getTransactionId(), 
            order.getTotalAmount()
        );

        if (refundIssued) {
            order.setStatus(OrderStatus.CANCELLED);
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}