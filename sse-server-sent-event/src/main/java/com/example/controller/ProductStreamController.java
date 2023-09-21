package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductStreamController {

    @Autowired
    private Flux<Product> productFlux;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductUpdates(){
        return this.productFlux;
    }

    @PostMapping
    public Mono<Product> insertProduct(@RequestBody Mono<Product> productMono){
        return this.productService.saveProduct(productMono);
    }
}