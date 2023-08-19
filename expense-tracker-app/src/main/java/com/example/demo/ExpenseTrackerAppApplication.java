package com.example.demo;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
public class ExpenseTrackerAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerAppApplication.class, args);
    }

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        long millis = System.currentTimeMillis();

        User user = User.builder()
                .id(1L)
                .age(30L)
                .password(passwordEncoder.encode("12345"))
                .createdAt(new Timestamp(millis))
                .name("john")
                .email("john.doe@gmail.com")
                .updatedAt(new Timestamp(millis))
                .build();
        userRepository.save(user);

        Expense expense = Expense.builder()
                .amount(new BigDecimal(10000))
                .category("Bill")
                .createdAt(new Timestamp(millis))
                .date(new Date(millis))
                .name("Water Bills")
                .description("Water Bill")
                .updatedAt(new Timestamp(millis))
                .amount(new BigDecimal("10000"))
                .user(user)
                .build();
        expenseRepository.save(expense);
    }
}