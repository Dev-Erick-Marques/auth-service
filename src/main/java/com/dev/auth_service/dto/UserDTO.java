package com.dev.auth_service.dto;

import lombok.NonNull;

public record UserDTO(
        @NonNull
        String email,
        @NonNull
        String password) {
}
