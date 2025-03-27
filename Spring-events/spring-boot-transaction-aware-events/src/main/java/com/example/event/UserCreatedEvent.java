package com.example.event;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    private final String username;
    private final String email;

    public UserCreatedEvent(Object source, String username, String email) {
        super(source);
        this.username = username;
        this.email = email;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}