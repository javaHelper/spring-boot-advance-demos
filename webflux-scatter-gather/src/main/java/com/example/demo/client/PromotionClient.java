package com.example.demo.client;

import com.example.demo.dto.Promotion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {
    private final WebClient client;

    public PromotionClient(WebClient.Builder builder) {
        this.client = builder
                .baseUrl("http://localhost:3000/promotions/")
                .build();
    }

    public Mono<Promotion> getPromotion(Integer productId){
        return this.client
                .get()
                .uri("{productId}", productId)
                .retrieve()
                .bodyToMono(Promotion.class)
                .onErrorReturn(new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31))); // if no result, it returns 404, so switch to no promotion
    }
}