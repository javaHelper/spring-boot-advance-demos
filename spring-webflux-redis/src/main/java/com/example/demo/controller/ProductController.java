package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public Mono<ProductDto> getProduct(@PathVariable Integer id){
        return this.productService.getProduct(id);
    }

    @PatchMapping("{id}")
    public Mono<Void> updateProduct(@PathVariable Integer id, @RequestBody Mono<ProductDto> mono){
        return this.productService.updateProduct(id, mono);
    }
}