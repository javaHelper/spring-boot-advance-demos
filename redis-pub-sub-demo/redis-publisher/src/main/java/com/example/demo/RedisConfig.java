package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RedisConfig {
	private static final String JOKE_API_ENDPOINT = "https://official-joke-api.appspot.com/jokes/random";
	
	@Bean
	public WebClient webClient(){
		return WebClient
        		.builder()
                .baseUrl(JOKE_API_ENDPOINT)
                .build();
    }
	
	@Bean
	public ReactiveRedisOperations<String, Joke> jokeTemplate(LettuceConnectionFactory lettuceConnectionFactory){
		RedisSerializer<Joke> valueSerializer = new Jackson2JsonRedisSerializer<>(Joke.class);
		RedisSerializationContext<String, Joke> serializationContext = RedisSerializationContext.<String, Joke>newSerializationContext(RedisSerializer.string())
                .value(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<String, Joke>(lettuceConnectionFactory, serializationContext);
	}
}
