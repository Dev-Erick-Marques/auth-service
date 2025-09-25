package com.dev.auth_service.exceptions.exceptions;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Email already registered";

    public EmailNotFoundException() {
        super(MESSAGE);
    }

    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
