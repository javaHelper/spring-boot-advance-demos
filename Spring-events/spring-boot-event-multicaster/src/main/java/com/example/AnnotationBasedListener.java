package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedListener {

    @EventListener
    public void handleCustomEvent(CustomEvent event) {
        System.out.println("Annotation-based listener received: " + event.getMessage()
                + ":" + Thread.currentThread().getName());
    }

    @EventListener(condition = "#event.message.startsWith('important')")
    public void handleImportantEvents(CustomEvent event) {
        System.out.println("Important event received: " + event.getMessage()
                + ":" + Thread.currentThread().getName());
    }
}