package com.piomin;

import com.piomin.model.Transaction;
import com.piomin.repository.TransactionRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class RedisTransactionRepositoryTest {

    @Container
    static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        int port = redis.getFirstMappedPort();
        registry.add("spring.redis.port", () -> port);
    }

    @Autowired
    TransactionRepository repository;

    @Test
    @Order(1)
    void shouldAdd() {
        Transaction transaction = new Transaction(1L, 1000, new Date(), 20L, 40L);
        transaction = repository.save(transaction);
        assertNotNull(transaction);
    }

    @Test
    @Order(2)
    public void shouldFindByFromAccountId() {
        List<Transaction> transactions = repository.findByFromAccountId(20L);
        assertEquals(1, transactions.size());
    }

    @Test
    @Order(3)
    public void shouldFindByToAccountId() {
        List<Transaction> transactions = repository.findByToAccountId(40L);
        assertEquals(1, transactions.size());
    }

}