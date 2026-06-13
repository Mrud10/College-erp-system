package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1 - get Authorization header
        String authHeader = request.getHeader("Authorization");

        // Step 2 - check if header exists and starts with Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // Step 3 - extract token
            String token = authHeader.substring(7);

            // Step 4 - validate token
            if (jwtUtil.isTokenValid(token)) {

                // Step 5 - extract email and role
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);

                // Step 6 - create authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                // Step 7 - tell Spring Security this user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Step 8 - continue to next filter
        filterChain.doFilter(request, response);
    }
}