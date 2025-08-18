package com.example.repository;

import com.example.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Simple pagination without entity graph
    Page<Order> findAll(Pageable pageable);

    // Pagination with named entity graph
    @EntityGraph(value = "Order.withCustomer")
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    // Dynamic entity graph with pagination
    @EntityGraph(attributePaths = {"customer", "shippingAddress"})
    Page<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    // Complex query with multiple joins and pagination
    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.name LIKE %:name%")
    @EntityGraph(value = "Order.simple")
    Page<Order> findByCustomerNameContaining(@Param("name") String name, Pageable pageable);

    // Custom query with multiple conditions and entity graph
    @EntityGraph(value = "Order.withCustomerAndItems")
    @Query("SELECT o FROM Order o WHERE o.totalAmount > :minAmount AND o.orderDate >= :sinceDate")
    Page<Order> findHighValueOrdersSince(
            @Param("minAmount") BigDecimal minAmount,
            @Param("sinceDate") LocalDateTime sinceDate,
            Pageable pageable);
}