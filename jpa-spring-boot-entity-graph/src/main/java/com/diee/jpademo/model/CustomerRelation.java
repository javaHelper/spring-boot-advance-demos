package com.diee.jpademo.model;

public enum CustomerRelation {

    CUSTOMER_WITH_ORDERS("customer-with-orders"),
    CUSTOMER_WITH_ORDERS_AND_DETAILS("customer-with-orders-and-details");

    private String name;

    CustomerRelation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
