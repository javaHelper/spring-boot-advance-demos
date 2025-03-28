package com.example.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderRequest {
    private String customerId;
    private String productId;
    private int quantity;
    private BigDecimal totalAmount;
    private String currency;
}