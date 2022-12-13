package com.example.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Data
@Document(collection = "purchase_order")
public class PurchaseOrder {

    @Id
    private String id;
    private User user;
    private Product product;
    private double price;
}
