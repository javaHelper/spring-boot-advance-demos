package com.example.userservice.service;

import java.util.List;

import com.example.userservice.dto.UserDto;

public interface UserService {
    Long createUser(UserDto userDto);
    void updateUser(UserDto userDto);
    List<UserDto> findAllUsers();
}