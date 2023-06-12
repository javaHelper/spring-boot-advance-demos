package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class SpringBootElasticsearchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticsearchDemoApplication.class, args);
	}

}