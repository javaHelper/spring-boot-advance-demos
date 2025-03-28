package com.example.exception;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}