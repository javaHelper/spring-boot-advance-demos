package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    public String getUserInfo() {
        sleep(1000);
        return "User Info";
    }

    public String getOrders() {
        sleep(1500);
        return "Order History";
    }

    public String getRecommendations() {
        sleep(1200);
        return "Recommendations";
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
