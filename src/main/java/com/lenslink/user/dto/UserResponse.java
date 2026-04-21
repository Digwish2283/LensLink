package com.lenslink.user.dto;

import com.lenslink.user.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private Set<Role> roles;
}