package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedListener {

    @Async
    @EventListener
    public void handleCustomEvent(CustomEvent event) {
        System.out.println("(1) Annotation-based listener received: " + event.getMessage() + ":" + Thread.currentThread().getName());
    }

    @Async
    @EventListener
    public void handleCustomEvent1(CustomEvent event) {
        System.out.println("(2) Annotation-based listener received: " + event.getMessage() + ":" + Thread.currentThread().getName());
    }

    @Async
    @EventListener(condition = "#event.message.startsWith('important')")
    public void handleImportantEvents(CustomEvent event) {
        System.out.println("## Important event received: " + event.getMessage() + ":" + Thread.currentThread().getName());
    }
}