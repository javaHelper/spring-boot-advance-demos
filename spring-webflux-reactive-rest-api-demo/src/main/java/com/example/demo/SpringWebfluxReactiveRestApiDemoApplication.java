package com.example.demo;

import com.example.demo.model.Tweet;
import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@EnableReactiveMongoRepositories
@SpringBootApplication
public class SpringWebfluxReactiveRestApiDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxReactiveRestApiDemoApplication.class, args);
	}

	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Tweet> tweets = List.of(Tweet.builder().id(UUID.randomUUID().toString())
						.text("RT @DrLoupis: Nothing says “trust the science” like asking for the data to be hidden for 75 years.")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot Rest API")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot Microservices")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot Reactive Microservices")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot Event Driven Microservices")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot and Spring Cloud and Azure")
						.createdAt(LocalDate.now()).build(),
				Tweet.builder().id(UUID.randomUUID().toString())
						.text("Java and Spring Boot and ")
						.createdAt(LocalDate.now()).build()
				);

		Flux<Tweet> tweetFlux = this.tweetRepository.saveAll(Flux.fromIterable(tweets));

		this.tweetRepository.deleteAll()
				.thenMany(tweetFlux)
				.thenMany(this.tweetRepository.findAll())
				.subscribe(System.out::println);
	}
}