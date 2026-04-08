package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/secure")
    public String secure(@AuthenticationPrincipal Jwt jwt) {
        return "Hello " + jwt.getSubject();
    }

    @GetMapping("/public")
    public String publicApi() {
        return "Public API";
    }
}