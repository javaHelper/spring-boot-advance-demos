package com.example.demo.consumer;

import com.example.demo.config.MessagingConfig;
import com.example.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("----------------------------");
        System.out.println("Message received from queue : " + orderStatus);
    }
}