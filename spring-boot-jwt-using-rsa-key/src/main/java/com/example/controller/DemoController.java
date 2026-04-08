package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Welcome %s! Your authorities: %s",
                jwt.getSubject(), jwt.getClaimAsString("scope"));
    }
}