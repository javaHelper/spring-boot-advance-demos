package com.example.service;

public interface NotificationService {
    void sendEmail(String recipient, String subject, String body);
    void logNotification(String message);
}