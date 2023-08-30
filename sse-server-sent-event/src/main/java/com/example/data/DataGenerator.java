package com.example.data;

import com.example.model.Product;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataGenerator {
    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void getProducts(){
        Faker faker = new Faker();
        for (int i = 1; i <= 1000; i++) {
            String productName = faker.commerce().productName();
            String color = faker.commerce().color();
            String promotionCode = faker.commerce().promotionCode();
            String price = faker.commerce().price();

            Product product = Product.builder()
                    .productId(Integer.valueOf(i))
                    .productName(productName)
                    .color(color)
                    .promotionCode(promotionCode)
                    .price(price)
                    .build();
            products.add(product);
        }
    }

    public Product findProduct(){
        int i = ThreadLocalRandom.current().nextInt(1, 1000);
        return products.get(i);
    }
}