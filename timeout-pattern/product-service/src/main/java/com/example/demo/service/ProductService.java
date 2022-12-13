package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.RatingServiceClient;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductDto;

@Service
public class ProductService {

	private Map<Integer, Product> map;

	@Autowired
	private RatingServiceClient ratingServiceClient;

	@PostConstruct
	private void init(){
		this.map = Map.of(
				1, Product.of(1, "Blood On The Dance Floor", 12.45),
				2, Product.of(2, "The Eminem Show", 12.12)
				);
	}

	
	public CompletionStage<ProductDto> getProductDto(int productId){
		
		return this.ratingServiceClient.getProductRatingDto(1)
				.thenApply(productRatingDto -> {
					Product product = this.map.get(productId);
					return ProductDto.of(productId, product.getDescription(), product.getPrice(), productRatingDto);
				});
	}

}