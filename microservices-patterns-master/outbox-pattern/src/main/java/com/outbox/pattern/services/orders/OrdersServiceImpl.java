package com.outbox.pattern.services.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.outbox.pattern.domain.OrderDomain;
import com.outbox.pattern.entity.OrderEntity;
import com.outbox.pattern.entity.OutboxEventEntity;
import com.outbox.pattern.exception.OrderProcessingException;
import com.outbox.pattern.repositories.OrderRepository;
import com.outbox.pattern.repositories.OutboxRepository;
import com.outbox.pattern.utils.OrderMapper;
import com.outbox.pattern.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OutboxRepository outboxRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public UUID createOrder(OrderDomain orderDomain) {
        OrderEntity orderEntity = createOrderInDatabase(orderDomain);
        saveOrderEventToDatabase(orderEntity, OrderStatus.CREATED);
        return orderEntity.getId();
    }

    private OrderEntity createOrderInDatabase(OrderDomain orderDomain) {
        try {
            OrderEntity orderEntity = orderMapper.orderEntityToOrderDomain(orderDomain);
            orderEntity.setStatus(OrderStatus.CREATED);
            return orderRepository.save(orderEntity);
        } catch (Exception e) {
            throw new OrderProcessingException("Error processing order creation", e.getMessage());
        }
    }

    private void saveOrderEventToDatabase(OrderEntity orderEntity, OrderStatus eventType) {
        OutboxEventEntity outboxEvent = new OutboxEventEntity();
        try {
            outboxEvent.setEventPayload(objectMapper.writeValueAsString(orderEntity));
            outboxEvent.setEventType(eventType);
            outboxRepository.save(outboxEvent);
        } catch (JsonProcessingException e) {
            throw new OrderProcessingException("Error processing order event creation", e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean cancelOrder(UUID orderUUID) {
        OrderEntity orderEntity = getOrderEntity(orderUUID);
        orderRepository.updateStatus(orderUUID, OrderStatus.CANCELLED);
        saveOrderEventToDatabase(orderEntity, OrderStatus.CANCELLED);
        return true;
    }

    private OrderEntity getOrderEntity(UUID orderUUID) {
        return orderRepository.findById(orderUUID)
                .orElseThrow(() -> new OrderProcessingException(String.format("Order entity with id %s not found", orderUUID), "NOT_FOUND"));
    }
}