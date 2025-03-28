package com.example.service;

public interface AuditLogger {
    void logEvent(String eventType, String message);
}