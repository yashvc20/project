package com.example.Controller;

import com.example.dto.LoginRequest;
import com.example.dto.AuthResponse;
import com.example.Entity.User;
import com.example.Service.UserService;
import com.example.Security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        logger.info("Login attempt for email: {}", request.getEmail());

        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error("Login failed: user not found with email {}", request.getEmail());
                    return new RuntimeException("Invalid credentials");
                });

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.error("Login failed: invalid password for email {}", request.getEmail());
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        logger.info("Login successful for email: {} with role: {}", user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }
}
