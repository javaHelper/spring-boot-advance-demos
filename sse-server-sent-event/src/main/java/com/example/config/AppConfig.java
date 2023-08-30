package com.example.config;

import com.example.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class AppConfig {

    @Bean
    public Sinks.Many<Product> sink(){
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<Product> productBroadCast(){
        return sink().asFlux();
    }
}