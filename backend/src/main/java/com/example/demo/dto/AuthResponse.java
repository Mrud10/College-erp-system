package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;
    private String email;

    public AuthResponse(String token, String role, String email) {
        this.token = token;
        this.role = role;
        this.email = email;
    }
}