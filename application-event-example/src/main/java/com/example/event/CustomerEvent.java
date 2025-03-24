package com.example.event;


import com.example.model.Customer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CustomerEvent extends ApplicationEvent {
    private final Customer customer;

    public CustomerEvent(Object source, Customer customer) {
        super(source);
        this.customer = customer;
    }
}