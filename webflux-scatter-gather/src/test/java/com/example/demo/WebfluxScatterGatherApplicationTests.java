package com.example.demo;

import com.example.demo.client.ProductClient;
import com.example.demo.client.PromotionClient;
import com.example.demo.client.ReviewClient;
import com.example.demo.dto.ProductAggregate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class WebfluxScatterGatherApplicationTests {
	@Autowired
	private ProductClient productClient;
	@Autowired
	private PromotionClient promotionClient;
	@Autowired
	private ReviewClient reviewClient;

	@Test
	void contextLoads() {
		Integer productId = 1;
		Mono<ProductAggregate> productAggregateMono = Mono.zip(
						this.productClient.getProduct(productId),
						this.promotionClient.getPromotion(productId),
						this.reviewClient.getReviews(productId))
				.map(t -> ProductAggregate.create(t.getT1(), t.getT2(), t.getT3()));

		StepVerifier.create(productAggregateMono)
				.expectNextCount(1)
				.verifyComplete();
	}

}