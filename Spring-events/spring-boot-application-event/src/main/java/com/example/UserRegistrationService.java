package com.example;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final ApplicationEventPublisher eventPublisher;


    public UserRegistrationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username, String email){
        // Save user to database...
        System.out.println("User registered: " + username);

        // publish event
        eventPublisher.publishEvent(new UserRegisteredEvent(this,username,email));
    }
}