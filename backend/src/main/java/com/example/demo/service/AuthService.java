package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request) {

        // Step 1 - find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2 - check password
        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Step 3 - generate token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        // Step 4 - return token + role
        return new AuthResponse(token, user.getRole(), user.getEmail());
    }
}