package com.piomin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class SampleSpringRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringRedisApplication.class, args);
	}

}