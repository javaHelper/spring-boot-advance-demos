package com.outbox.pattern.exception;

public class OrderProcessingException extends RuntimeException {
    public OrderProcessingException(String message, String e) {
        super(message);
    }
}
