package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Mono<UserDto> register(@Valid @RequestBody Mono<UserDto> userDtoMono){
        return this.userService.registerUser(userDtoMono);
    }
}