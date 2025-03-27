package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {
    
    @Autowired
    private ApplicationEventMulticaster eventMulticaster;
    
    public void publishCustomEvent(String message) {
        CustomEvent event = new CustomEvent(this, message);
        eventMulticaster.multicastEvent(event);
    }
}