package com.example.demo.controller;

import com.example.demo.service.TweetService;
import com.example.demo.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets")
    public Flux<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PostMapping("/tweets")
    public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
        return tweetService.createTweets(tweet);
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId){
        return tweetService.getTweetById(tweetId);
    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Tweet tweet){
        return tweetService.updateTweet(tweetId, tweet);
    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId){
        return tweetService.deleteTweet(tweetId);
    }

    // Tweets are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> streamAllTweets() {
        return tweetService.streamAllTweets();
    }
}