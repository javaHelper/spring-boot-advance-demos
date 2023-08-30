package com.example.service;

import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
public class ProductService {

    @Autowired
    private Sinks.Many<Product> sink;

    public Mono<Product> saveProduct(Mono<Product> productMono){
        return productMono
                .doOnNext(System.out::println)
                .doOnNext(p -> sink.tryEmitNext(p));
    }
}