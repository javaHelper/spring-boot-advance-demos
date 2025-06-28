package com.example;

import com.github.javafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
public class EmployeeController {
    Faker faker = new Faker();

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestHeader("correlation-id") String correlationId) {
        System.out.println("Correlation ID "+ correlationId);

        List<Employee> employees = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new Employee(i,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        ThreadLocalRandom.current().nextInt(20, 80)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
