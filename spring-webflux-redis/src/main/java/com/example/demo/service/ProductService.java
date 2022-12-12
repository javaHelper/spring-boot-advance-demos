package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> getProduct(Integer id);
    Mono<Void> updateProduct(Integer id, Mono<ProductDto> productDto);
}