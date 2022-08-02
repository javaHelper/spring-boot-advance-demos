package com.example.demo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class SpringBootJasyptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJasyptApplication.class, args);
	}

}
