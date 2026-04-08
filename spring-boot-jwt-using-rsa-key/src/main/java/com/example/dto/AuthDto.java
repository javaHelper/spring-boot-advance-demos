package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDto {
    public record RegisterRequest(@NotBlank String username, @NotBlank String password) {}
    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record AuthResponse(String token) {}
}