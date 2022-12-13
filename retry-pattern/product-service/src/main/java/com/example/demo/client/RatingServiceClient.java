package com.example.demo.client;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ProductRatingDto;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RatingServiceClient {

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${rating.service.endpoint}")
	private String ratingService;

	@Retry(name = "ratingService", fallbackMethod = "getDefault")
	public CompletableFuture<ProductRatingDto> getProductRatingDto(int productId) {
		Supplier<ProductRatingDto> supplier = () -> this.restTemplate.getForEntity(this.ratingService + productId,
				ProductRatingDto.class).getBody();

		return CompletableFuture.supplyAsync(supplier);
	}

	public CompletableFuture<ProductRatingDto> getDefault(int productId, Throwable throwable) {
		System.out.println("== Fallback is called, since rating-service is down");
		return CompletableFuture.supplyAsync(() -> ProductRatingDto.of(0, Collections.emptyList()));
	}

}