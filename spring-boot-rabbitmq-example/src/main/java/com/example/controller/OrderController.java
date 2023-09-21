package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Order;
import com.example.publisher.OrderPublisher;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderPublisher orderPublisher;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        orderPublisher.publishOrder(order, restaurantName);
        return "Message Published Successfully!";
    }
}