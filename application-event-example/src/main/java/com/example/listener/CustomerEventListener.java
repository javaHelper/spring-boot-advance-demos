package com.example.listener;

import com.example.event.CustomerEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener implements ApplicationListener<CustomerEvent> {

    @Override
    public void onApplicationEvent(CustomerEvent event) {
        System.out.println("============");
        System.out.println(event);
    }
}