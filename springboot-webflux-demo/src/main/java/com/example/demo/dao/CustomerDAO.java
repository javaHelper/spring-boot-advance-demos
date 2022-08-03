package com.example.demo.dao;

import com.example.demo.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDAO {
    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDAO::sleepExecution)
                .peek(i -> System.out.println("Processing count : " + i))
                .mapToObj(i -> new Customer(i, "FirstName" + i, "LastName" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "FirstName" + i, "LastName" + i));
    }


    public Flux<Customer> getCustomerList() {
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Customer(i, "FirstName" + i, "LastName" + i));
    }
}