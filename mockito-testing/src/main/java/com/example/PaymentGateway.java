package com.example;

import java.math.BigDecimal;

public interface PaymentGateway {
    String authorizePayment(String customerId, BigDecimal amount, String currency);
    boolean capturePayment(String transactionId);
    boolean refundPayment(String transactionId, BigDecimal amount);
}