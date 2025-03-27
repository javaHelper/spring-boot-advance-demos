package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {
    @EventListener
    public void sendWelcomeEmail(UserRegisteredEvent event){
        System.out.println("Sending welcome email to "+ event.getEmail());
    }

    @EventListener
    public void createInitialSettings(UserRegisteredEvent event) {
        System.out.println("Creating initial settings for user: " + event.getUsername());
        // Create user preferences, default settings, etc.
    }
}