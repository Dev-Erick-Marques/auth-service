package com.dev.auth_service.dto;

import com.dev.auth_service.user.User;
import java.util.UUID;


public record UserRequestDTO(String email, UUID uuid) {
    public UserRequestDTO(User user) {
        this(user.getUserEmail(), user.getUserId());
    }
}

