package com.dev.auth_service.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record ErrorDTO(String timestamp,
                       int status,
                       String error,
                       String message,
                       String path) {
    public static ErrorDTO of (String message, HttpStatus status, String path) {
        return new ErrorDTO(
                Instant.now().truncatedTo(ChronoUnit.MILLIS).toString(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
    }
}
