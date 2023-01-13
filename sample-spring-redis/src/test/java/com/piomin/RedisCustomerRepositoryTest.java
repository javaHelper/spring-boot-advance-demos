package com.piomin;

import com.piomin.model.Account;
import com.piomin.model.Customer;
import com.piomin.repository.CustomerRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataRedisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class RedisCustomerRepositoryTest {

    @Container
    static final GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        int port = redis.getFirstMappedPort();
        registry.add("spring.redis.port", () -> port);
    }

    @Autowired
    CustomerRepository repository;

    @Test
    @Order(1)
    void shouldAdd() {
        Customer customer = new Customer(1L, "80010121098", "John Smith");
        customer.addAccount(new Account(1L, "1234567890", 2000));
        customer.addAccount(new Account(2L, "1234567891", 4000));
        customer.addAccount(new Account(3L, "1234567892", 6000));
        customer = repository.save(customer);
        assertNotNull(customer);
    }

    @Test
    @Order(2)
    void shouldFindByAccounts() {
        List<Customer> customers = repository.findByAccountsId(3L);
        assertEquals(1, customers.size());
        Customer customer = customers.get(0);
        assertNotNull(customer);
        assertEquals(1, customer.getId().longValue());
    }

    @Test
    @Order(3)
    void shouldFindByExternal() {
        Customer customer = repository.findByExternalId("80010121098");
        assertNotNull(customer);
        assertEquals(1, customer.getId().longValue());
    }
}