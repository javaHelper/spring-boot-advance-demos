package com.example;

import com.example.eventpublisher.EventPublisher;
import com.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationEventExampleApplication implements CommandLineRunner {

    @Autowired
    private EventPublisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEventExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = Customer.builder().id(1L)
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .build();

        publisher.publishEvent(customer);
        publisher.publishEventObject(customer);
    }
}