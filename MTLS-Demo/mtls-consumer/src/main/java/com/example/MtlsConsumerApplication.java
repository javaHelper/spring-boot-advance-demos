package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MtlsConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MtlsConsumerApplication.class, args);
	}

	private final ApiService apiService;

	public MtlsConsumerApplication(ApiService apiService) {
		this.apiService = apiService;
	}

	@Override
	public void run(String... args) throws Exception {
		String response = apiService.callApi();
		System.out.println("Response: " + response);
	}
}
