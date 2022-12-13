package com.example;

import com.example.repository.FreelancerRepository;
import com.example.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.time.Duration;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MongodbAggregationPipelineReactiveSpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongodbAggregationPipelineReactiveSpringDataApplication.class, args);
	}

	@Autowired
	private FreelancerRepository repository;

	@Autowired
	private FreelancerService service;

	@Override
	public void run(String... args) throws Exception {
		// approach - 1
		System.out.println("Approach-1: -------------------------\n");
		this.repository.getSkilledPeople()
				.doOnNext(System.out::println)
				.blockLast(Duration.ofSeconds(10));

		// approach - 2
		System.out.println("Approach-2: -------------------------\n");
		this.service.getSkilledPeople()
				.doOnNext(System.out::println)
				.blockLast(Duration.ofSeconds(10));
	}
}