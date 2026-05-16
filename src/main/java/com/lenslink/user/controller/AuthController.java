package com.lenslink.user.controller;

import com.lenslink.user.dto.LoginRequest;
import com.lenslink.user.dto.LoginResponse;
import com.lenslink.user.dto.RegisterRequest;
import com.lenslink.user.dto.UserResponse;
import com.lenslink.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}