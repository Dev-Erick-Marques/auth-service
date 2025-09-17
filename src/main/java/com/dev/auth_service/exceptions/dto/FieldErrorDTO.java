package com.dev.auth_service.exceptions.dto;

public record FieldErrorDTO(
        String field,
        String message) {
}
