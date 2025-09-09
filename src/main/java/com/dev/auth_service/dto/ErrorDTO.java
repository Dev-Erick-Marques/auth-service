package com.dev.auth_service.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorDTO(String timestamp,
                       int status,
                       String error,
                       String message,
                       String path) {
    public ErrorDTO(String message, HttpStatus status, String path) {
        this(
                Instant.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }
}
