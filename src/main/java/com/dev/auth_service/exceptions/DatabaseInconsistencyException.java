package com.dev.auth_service.exceptions;

public class DatabaseInconsistencyException extends RuntimeException {
    public DatabaseInconsistencyException(String message) {
        super(message);
    }
}
