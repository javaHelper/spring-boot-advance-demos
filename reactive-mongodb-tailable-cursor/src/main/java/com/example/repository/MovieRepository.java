package com.example.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import com.example.entity.Movie;

import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String>{

	@Tailable
    Flux<Movie> findByGenre(String genre);
}
