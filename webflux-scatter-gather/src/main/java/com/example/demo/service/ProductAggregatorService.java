package com.example.demo.service;

import com.example.demo.client.ProductClient;
import com.example.demo.client.PromotionClient;
import com.example.demo.client.ReviewClient;
import com.example.demo.dto.ProductAggregate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ProductAggregatorService {
    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> getProductById(Integer productId) {
        log.info("Get Products details for ProductId : {}", productId);
        return Mono.zip(
                        this.productClient.getProduct(productId),
                        this.promotionClient.getPromotion(productId),
                        this.reviewClient.getReviews(productId))
                .map(t -> ProductAggregate.create(t.getT1(), t.getT2(), t.getT3()));
    }
}