package com.example.demo.service;

import com.example.demo.model.Tweet;
import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    public Flux<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public Mono<Tweet> createTweets(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public Mono<ResponseEntity<Tweet>> getTweetById(String tweetId) {
        return tweetRepository.findById(tweetId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Tweet>> updateTweet(String tweetId, Tweet tweet) {
        return tweetRepository.findById(tweetId)
                .flatMap(existingTweet -> {
                    existingTweet.setText(tweet.getText());
                    return tweetRepository.save(existingTweet);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(String tweetId) {
        return tweetRepository.findById(tweetId)
                .flatMap(existingTweet -> tweetRepository.delete(existingTweet)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    public Flux<Tweet> streamAllTweets() {
        return tweetRepository.findAll();
    }
}