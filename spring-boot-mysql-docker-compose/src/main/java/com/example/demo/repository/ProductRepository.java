package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}