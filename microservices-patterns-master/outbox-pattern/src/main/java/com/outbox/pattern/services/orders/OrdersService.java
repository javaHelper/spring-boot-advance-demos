package com.outbox.pattern.services.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.outbox.pattern.domain.OrderDomain;

import java.util.UUID;

public interface OrdersService {
    UUID createOrder(OrderDomain orderDTO);
    boolean cancelOrder(UUID orderUUID);
}