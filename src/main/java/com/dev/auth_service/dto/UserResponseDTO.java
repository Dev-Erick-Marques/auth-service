package com.dev.auth_service.dto;

import com.dev.auth_service.domain.models.User;
import java.util.UUID;


public record UserResponseDTO(String email, UUID userId) {
    public UserResponseDTO(User user) {
        this(user.getUserEmail(), user.getUserId());
    }
}

