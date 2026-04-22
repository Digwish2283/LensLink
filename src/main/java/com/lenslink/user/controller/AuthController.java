package com.lenslink.user.controller;

import com.lenslink.user.dto.RegisterRequest;
import com.lenslink.user.dto.UserResponse;
import com.lenslink.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}