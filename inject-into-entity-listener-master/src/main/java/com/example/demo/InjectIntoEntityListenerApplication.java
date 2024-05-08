package com.example.demo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class InjectIntoEntityListenerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(InjectIntoEntityListenerApplication.class, args);
	}
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Stuff stuff = new Stuff();
		entityManager.persist(stuff);
		System.out.println(stuff.getFilledByEntityListener());
	}
}
