package com.example.service;

import com.example.dto.AccountDetails;
import com.example.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    public List<User> getAllUsers() {
        AccountDetails accountDetails = new AccountDetails("JOHN CURRRAN", "365027332671", "SAVING");

        User user1 = new User(3235, "john", "6474729899", "1234567890", 30, "johncn", "john123", accountDetails);

        return Stream.of(user1).collect(Collectors.toList());
    }

    public User getUser(int id) {
        return getAllUsers().stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}