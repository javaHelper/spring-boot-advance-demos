package com.example.listener;

import com.example.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventListener {

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        //System.out.println("Simple listener received user created event for: " + event.getUsername());
        // This will execute immediately, regardless of transaction status
    }
}