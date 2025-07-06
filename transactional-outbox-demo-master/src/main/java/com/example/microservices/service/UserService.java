package com.example.microservices.service;

import com.example.microservices.dto.UserRequestDto;
import com.example.microservices.entity.Outbox;
import com.example.microservices.entity.User;
import com.example.microservices.mapper.UserMapper;
import com.example.microservices.mapper.UserRequestDtoMapper;
import com.example.microservices.repository.OutboxRepository;
import com.example.microservices.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OutboxRepository outboxRepository;
    private final UserRequestDtoMapper userRequestDtoMapper;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, OutboxRepository outboxRepository,
                       UserRequestDtoMapper userRequestDtoMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.outboxRepository = outboxRepository;
        this.userRequestDtoMapper = userRequestDtoMapper;
        this.userMapper = userMapper;
    }

    @Transactional
    public User createUser(UserRequestDto userRequestDto) {
        try {
            User user = userRequestDtoMapper.mapToUserEntity(userRequestDto);

            user = userRepository.save(user);

            Outbox outbox = userMapper.mapToOutBoxEntity(user);

            outboxRepository.save(outbox);

            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
