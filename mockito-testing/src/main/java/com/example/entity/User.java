package com.example.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String email;
    private boolean activated;
    
    // Constructors, getters, setters
    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.activated = false;
    }
    
    // Additional methods
    public void activate() {
        this.activated = true;
    }
}