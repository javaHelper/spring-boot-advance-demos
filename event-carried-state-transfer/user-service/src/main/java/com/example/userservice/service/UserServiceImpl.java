package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.Users;
import com.example.userservice.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Override
    public Long createUser(UserDto userDto) {
        Users user = Users.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .build();
        return usersRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        usersRepository.findById(userDto.getId())
                .ifPresent(user -> {
                    user.setFirstname(userDto.getFirstname());
                    user.setLastname(userDto.getLastname());
                    user.setEmail(userDto.getEmail());

                    // Raise a kafka event
                    this.raiseEvent(userDto);
                });
    }

    @Override
    public List<UserDto> findAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(u -> {
                	UserDto dto = new UserDto();
                	dto.setId(u.getId());
                	dto.setFirstname(u.getFirstname());
                	dto.setLastname(u.getLastname());
                	dto.setEmail(u.getEmail());
                	return dto;
                })
                .collect(Collectors.toList());
    }

    private void raiseEvent(UserDto userDto) {
        try {
            String value = OBJECT_MAPPER.writeValueAsString(userDto);
            this.kafkaTemplate.sendDefault(userDto.getId(), value);
        } catch (JsonProcessingException e) {
           LOGGER.error("UserServiceImpl | raiseEvent | ", e.getMessage());
        }
    }
}