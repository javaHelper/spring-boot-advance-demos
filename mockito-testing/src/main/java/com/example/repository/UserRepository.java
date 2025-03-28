package com.example.repository;

import com.example.entity.User;

public interface UserRepository {
    void save(User user);
    void update(User user);
    void sendActivationEmail(String email);
    User findById(String userId);
}