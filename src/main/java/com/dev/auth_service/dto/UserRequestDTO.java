package com.dev.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;

public record UserRequestDTO(
        @NotNull(message = "Must be not null")
        @Email(message = "Must be a valid email")
        String email,

        @NonNull
        String password,
        @NotNull
        String username) {
}
