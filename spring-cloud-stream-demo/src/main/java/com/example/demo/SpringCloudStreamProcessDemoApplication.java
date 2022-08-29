package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SpringCloudStreamProcessDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamProcessDemoApplication.class, args);
	}

	// uppercase-in-0
	// uppercase-out-0
	@Bean
	public Function<String, String> toUppercase(){
		//return s -> s.toUpperCase();
		return String::toUpperCase;
	}
}