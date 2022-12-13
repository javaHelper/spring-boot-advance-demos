package com.example.demo.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductRatingDto;
import com.example.demo.service.RatingService;

@RestController
@RequestMapping("ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@GetMapping("{prodId}")
	public ResponseEntity<ProductRatingDto> getRating(@PathVariable int prodId) {
		ProductRatingDto productRatingDto = this.ratingService.getRatingForProduct(prodId);
		return this.failRandomly(productRatingDto);
	}

	private ResponseEntity<ProductRatingDto> failRandomly(ProductRatingDto productRatingDto) {
		int random = ThreadLocalRandom.current().nextInt(1, 4);
		if (random < 2) {
			return ResponseEntity.status(500).build();
		} else if (random < 3) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(productRatingDto);
	}

}
