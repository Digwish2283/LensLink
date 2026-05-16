package com.lenslink.user.service;

import com.lenslink.user.dto.LoginRequest;
import com.lenslink.user.dto.LoginResponse;
import com.lenslink.user.dto.RegisterRequest;
import com.lenslink.user.dto.UserResponse;

public interface UserService {
    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}