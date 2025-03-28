package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final AuditLogger auditLogger;

    public UserService(UserRepository userRepository, AuditLogger auditLogger) {
        this.userRepository = userRepository;
        this.auditLogger = auditLogger;
    }

    public void registerUser(User user) {
        userRepository.save(user);
        userRepository.sendActivationEmail(user.getEmail());
        auditLogger.logEvent("USER_REGISTERED", "New user registered: " + user.getUsername());
    }

    public void activateUser(String userId) {
        // In a real implementation, we would retrieve the user first
        User user = new User(userId, "temp", "temp@example.com");
        user.activate();
        userRepository.update(user);
    }

    public void updateUserEmail(String userId, String newEmail) {
        User user = userRepository.findById(userId);
        user.setEmail(newEmail);
        userRepository.save(user);
        auditLogger.logEvent("EMAIL_UPDATED", userId + " updated email to " + newEmail);
    }
}