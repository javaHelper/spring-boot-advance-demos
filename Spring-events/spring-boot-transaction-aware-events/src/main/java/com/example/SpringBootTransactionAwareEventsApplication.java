package com.example;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTransactionAwareEventsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionAwareEventsApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=== TEST 1: Successful transaction ===");
		userService.createUser("success_user", "success@example.com", false);

		System.out.println("\n=== TEST 2: Failing transaction ===");
		try {
			userService.createUser("fail_user", "fail@example.com", true);
		} catch (Exception e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
	}
}