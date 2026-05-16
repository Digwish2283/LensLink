package com.lenslink.user.service;

import com.lenslink.common.exception.EmailAlreadyExistsException;
import com.lenslink.security.jwt.JwtService;
import com.lenslink.user.dto.LoginRequest;
import com.lenslink.user.dto.LoginResponse;
import com.lenslink.user.dto.RegisterRequest;
import com.lenslink.user.dto.UserResponse;
import com.lenslink.user.entity.User;
import com.lenslink.user.enums.Role;
import com.lenslink.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request) {

        // duplicate email check
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException("Email already exists");
                });

        // create user
        User user = new User();
        user.setEmail(request.getEmail());
        //user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRoles(Set.of(Role.CLIENT));

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .build();
    }
}