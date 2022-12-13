package com.example.service;

import com.example.dto.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.AssertTrue;

@Service
public class UserService {

    @AssertTrue
    public Mono<UserDto> registerUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .doOnNext(System.out::println);
    }
}