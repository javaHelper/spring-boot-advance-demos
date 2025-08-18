package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Order.withCustomerAndItems",
                attributeNodes = {
                        @NamedAttributeNode("customer"),
                        @NamedAttributeNode(value = "orderItems", subgraph = "orderItems"),
                        @NamedAttributeNode("payment")
                },
                subgraphs = {
                        @NamedSubgraph(name = "orderItems",
                                attributeNodes = {
                                        @NamedAttributeNode("product"),
                                        @NamedAttributeNode("addons")
                                }
                        )
                }
        ),
        @NamedEntityGraph(name = "Order.simple",
                attributeNodes = {
                        @NamedAttributeNode("customer")
                }
        )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

}