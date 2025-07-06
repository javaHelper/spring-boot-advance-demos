package com.outbox.pattern.controller;

import com.outbox.pattern.domain.OrderDomain;
import com.outbox.pattern.services.orders.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // Post an order
    @PostMapping(produces = "application/json")
    public ResponseEntity<UUID> createOrder(@RequestBody @Valid OrderDomain orderDomain) {
        UUID order = ordersService.createOrder(orderDomain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(order);
    }

    // Cancel an order
    @DeleteMapping(value = "/{orderUUID}", produces = "application/json")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable @Valid UUID orderUUID) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ordersService.cancelOrder(orderUUID));
    }
}
