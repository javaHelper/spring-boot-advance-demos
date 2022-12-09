package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaDynamicUpdateDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaDynamicUpdateDemoApplication.class, args);
    }

    @Autowired
    private ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = List.of(
                Product.builder().name("Sony TV").price(10).productType("Digital").description("Sony Digital TV").build(),
                Product.builder().name("Mac Air").price(1000).productType("Digital").description("Apple Mac Book Pro").build(),
                Product.builder().name("Headphones").price(5).productType("Digital").description("Zebronics Headphones").build(),
                Product.builder().name("Samsung").price(10).productType("Digital").description("Samsung Mobile").build()
        );
        repository.saveAll(products);
    }
}