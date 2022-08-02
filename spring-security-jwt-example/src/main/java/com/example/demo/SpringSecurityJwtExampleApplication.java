package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        saveUser();
    }

    private void saveUser() {
        List<User> users = Stream.of(
                        new User(101, "javatechie", "password", "javatechie@gmail.com"),
                        new User(102, "john", "doe", "john@gmail.com"),
                        new User(103, "jane", "doe", "jane@gmail.com"),
                        new User(104, "mike", "doe", "mike@gmail.com")
                )
                .collect(Collectors.toList());
        repository.saveAll(users);
    }
}
