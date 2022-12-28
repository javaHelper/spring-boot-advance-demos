package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PublisherService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ReactiveRedisOperations<String, Joke> redisTemplate;

    @Value("${topic.name:joke-channel}")
    private String topic;


    @Scheduled(fixedRate = 3000)
    public void publish() {
        this.webClient
        		.get()
                .retrieve()
                .bodyToMono(Joke.class)
                .flatMap(joke -> this.redisTemplate.convertAndSend(topic, joke))
                .subscribe();
    }
}
