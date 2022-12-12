package com.example;

import com.example.entity.Freelancer;
import com.example.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveMongoCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoCrudApplication.class, args);
	}

	@Autowired
	private FreelancerRepository repository;

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		List<Freelancer> freelancers = List.of(
				Freelancer.builder().name("sam").age(40).skills(Arrays.asList("js","react","python")).build(),
				Freelancer.builder().name("jack").age(38).skills(Arrays.asList("js", "angular", "postgres")).build(),
				Freelancer.builder().name("james").age(30).skills(Arrays.asList("java", "reactor", "mongo")).build(),
				Freelancer.builder().name("smith").age(32).skills(Arrays.asList("java", "reactor", "mongo")).build()
		);

		repository.saveAll(freelancers);
	}
}