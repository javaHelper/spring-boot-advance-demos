package com.example.listener;

import com.example.event.CustomerEvent;
import com.example.model.Customer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class CustomerListener {

    @EventListener
    public void handleEvent(CustomerEvent customerEvent){
        System.out.println(customerEvent.getCustomer());
    }

    @EventListener
    public void handleObject(Customer customer){
        System.out.println(customer);
    }
}