package com.example.demo.util;

import com.example.demo.entity.Product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
    public static List<Product> createProducts() {
        return IntStream.range(1, 100)
                .mapToObj(i -> Product.builder().name("Product" + i).price(i).quantity(1).build())
                .collect(Collectors.toList());
    }
}