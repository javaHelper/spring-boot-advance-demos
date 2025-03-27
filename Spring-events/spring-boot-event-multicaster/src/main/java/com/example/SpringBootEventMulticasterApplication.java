package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootEventMulticasterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEventMulticasterApplication.class, args);
	}

	@Autowired
	private EventPublisherService eventPublisherService;

	@Override
	public void run(String... args) throws Exception {
		eventPublisherService.publishCustomEvent("Hello from direct multicaster!");
		eventPublisherService.publishCustomEvent("important Hello from direct multicaster!");
	}
}