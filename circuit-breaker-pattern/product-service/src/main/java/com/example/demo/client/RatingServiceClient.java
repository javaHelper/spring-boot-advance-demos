package com.example.demo.client;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ProductRatingDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RatingServiceClient {

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${rating.service.endpoint}")
	private String ratingService;

	@Retry(name = "ratingService", fallbackMethod = "getDefault")
	@CircuitBreaker(name = "ratingService", fallbackMethod = "getDefault")
	public ProductRatingDto getProductRatingDto(int productId) {
		return this.restTemplate.getForEntity(this.ratingService + productId, ProductRatingDto.class).getBody();
	}

	public ProductRatingDto getDefault(int productId, Throwable throwable) {
		System.out.println("## Fallback is called, since downstream system is down.");
		return ProductRatingDto.of(0, Collections.emptyList());
	}

}