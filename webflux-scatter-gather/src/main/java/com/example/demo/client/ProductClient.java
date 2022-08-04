package com.example.demo.client;

import com.example.demo.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {
    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder){
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:3000/products/")
                .build();
    }

    public Mono<Product> getProduct(Integer productId){
        return this.webClient
                .get()
                .uri("{productId}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(throwable -> Mono.empty());
    }
}