package com.example.service;

public interface InventoryService {
    boolean isAvailable(String productId, int quantity);
    boolean reserve(String productId, int quantity);
    boolean release(String productId, int quantity);
}