package com.example.service;

import com.example.OrderStatus;
import com.example.entity.Order;
import com.example.entity.OrderRequest;

public interface OrderService {
    Order processOrder(OrderRequest request);
    OrderStatus checkOrderStatus(String orderId);
    boolean cancelOrder(String orderId);
}