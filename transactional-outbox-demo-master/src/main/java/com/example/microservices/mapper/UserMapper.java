package com.example.microservices.mapper;

import com.example.microservices.constant.Aggregate;
import com.example.microservices.entity.Outbox;
import com.example.microservices.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ObjectMapper objectMapper;

    public UserMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Outbox mapToOutBoxEntity(User user) throws JsonProcessingException {
        return Outbox.builder()
                .aggregate(Aggregate.USER)
                .message(objectMapper.writeValueAsString(user))
                .isDelivered(false)
                .build();
    }
}
