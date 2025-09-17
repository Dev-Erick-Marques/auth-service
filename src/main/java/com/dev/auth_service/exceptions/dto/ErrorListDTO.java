package com.dev.auth_service.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record ErrorListDTO(
        String timestamp,
        int status,
        String error,
        String message,
        List<FieldErrorDTO> errors,
        String path) {

    public static ErrorListDTO of (String message, HttpStatus status, List<FieldErrorDTO> errors, String path) {
        return new ErrorListDTO(
                Instant.now().truncatedTo(ChronoUnit.MILLIS).toString(),
                status.value(),
                status.getReasonPhrase(),
                message,
                errors,
                path
        );
    }
}
