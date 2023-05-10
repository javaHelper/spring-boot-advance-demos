package com.example.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Movie;
import com.example.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
public class MovieController {

	@Autowired
	private MovieRepository repository;

	@GetMapping(value = "/movie/{genre}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Movie> getMovies(@PathVariable String genre) {
		return this.repository.findByGenre(genre)
				.repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(1)))
				.subscribeOn(Schedulers.boundedElastic());
	}

}