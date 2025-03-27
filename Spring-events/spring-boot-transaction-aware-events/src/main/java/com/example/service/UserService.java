package com.example.service;

import com.example.entity.User;
import com.example.event.UserCreatedEvent;
import com.example.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    public UserService(ApplicationEventPublisher eventPublisher, UserRepository userRepository) {
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(String username, String email, boolean shouldFail) {
        User user = new User(username, email);
        userRepository.save(user);
        
        // Publish the event
        eventPublisher.publishEvent(new UserCreatedEvent(this, username, email));
        
        System.out.println("User created (transaction not yet committed)");
        
        if (shouldFail) {
            throw new RuntimeException("Simulated error to trigger rollback");
        }
    }
}