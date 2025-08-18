package com.example.service;

import com.example.dto.OrderDto;
import com.example.entity.Order;
import com.example.repository.OrderRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderDto> getOrdersByCustomer(Long customerId, int page, int size) {
        Page<Order> orders = orderRepository.findByCustomerId(
                customerId,
                PageRequest.of(page, size, Sort.by("orderDate").descending())
        );

        return orders.map(this::convertToDto);
    }

    public Page<OrderDto> getHighValueOrders(BigDecimal minAmount, LocalDateTime sinceDate, int page, int size) {
        Page<Order> orders = orderRepository.findHighValueOrdersSince(
                minAmount,
                sinceDate,
                PageRequest.of(page, size, Sort.by("totalAmount").descending())
        );

        return orders.map(this::convertToDto);
    }

    public Page<OrderDto> searchOrdersByCustomerName(String name, int page, int size) {
        Page<Order> orders = orderRepository.findByCustomerNameContaining(name, PageRequest.of(page, size));
        return orders.map(this::convertToDto);
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());

        if (Hibernate.isInitialized(order.getCustomer())) {
            dto.setCustomerName(order.getCustomer().getName());
        }

        if (Hibernate.isInitialized(order.getOrderItems())) {
            dto.setItemCount(order.getOrderItems().size());
        }

        return dto;
    }
}