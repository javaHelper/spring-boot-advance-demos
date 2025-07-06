package com.example.microservices.consumer;

import com.example.microservices.dto.UserRequestDto;
import com.example.microservices.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "user-events")
    public void listen(String data) {
        System.out.println("------------------");
        User user = objectMapper.readValue(data, User.class);
        System.out.println(user);
    }
}
