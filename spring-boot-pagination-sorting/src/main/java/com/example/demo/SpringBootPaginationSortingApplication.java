package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.Util;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Product API", version = "1.0.0", description = "Product API Docs")
)
public class SpringBootPaginationSortingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPaginationSortingApplication.class, args);
	}
	@Autowired
	private ProductRepository repository;

	@Override
	public void run(String... args) throws Exception {
		final List<Product> products = Util.createProducts();
		//repository.saveAll(products);
	}
}