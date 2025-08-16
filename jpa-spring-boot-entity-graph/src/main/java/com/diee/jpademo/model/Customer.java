package com.diee.jpademo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;


@Setter
@Getter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "customer-with-orders",
                attributeNodes = {
                        @NamedAttributeNode("orders")
                }

        ),
        @NamedEntityGraph(
                name = "customer-with-orders-and-details",
                attributeNodes = {
                        @NamedAttributeNode(value = "orders", subgraph = "order-details"),
                },

                subgraphs = {@NamedSubgraph(
                        name = "order-details",
                        attributeNodes = {
                                @NamedAttributeNode("orderDetail")
                        }
                )}
        )
})

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    private Integer customerNumber;

    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private Float creditLimit;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
