package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
    public Product save(Product item);
    public List<Product> findAll();
}