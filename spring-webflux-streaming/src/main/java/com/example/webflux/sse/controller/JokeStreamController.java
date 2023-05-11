package com.example.webflux.sse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.sse.dto.Joke;

import reactor.core.publisher.Flux;

@RestController
public class JokeStreamController {
	@Autowired
    private Flux<Joke> flux;

    @GetMapping(value = "joke", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Joke> getJoke(){
        return flux;
    }
}
