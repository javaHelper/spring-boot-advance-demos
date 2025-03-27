package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MainApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Autowired
    private AnotherEventPublisher anotherEventPublisher;

    @Override
    public void run(String... args) throws Exception {
        anotherEventPublisher.publishEvent("Hello from ApplicationEventPublisher");
        anotherEventPublisher.publishEvent("important: Critical message!");
    }
}