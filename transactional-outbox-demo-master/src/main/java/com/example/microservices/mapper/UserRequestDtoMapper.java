package com.example.microservices.mapper;

import com.example.microservices.dto.UserRequestDto;
import com.example.microservices.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoMapper {

    public User mapToUserEntity(UserRequestDto userRequestDto) {
        return User.builder()
                .firstname(userRequestDto.getFirstName())
                .lastname(userRequestDto.getLastName())
                .dob(userRequestDto.getDob())
                .address(userRequestDto.getAddress())
                .email(userRequestDto.getEmail())
                .build();
    }
}
