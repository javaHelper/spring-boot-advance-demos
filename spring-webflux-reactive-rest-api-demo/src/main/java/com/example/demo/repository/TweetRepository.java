package com.example.demo.repository;

import com.example.demo.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}