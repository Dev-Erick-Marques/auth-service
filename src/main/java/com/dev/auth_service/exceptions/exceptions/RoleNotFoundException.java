package com.dev.auth_service.exceptions.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends RuntimeException {
  private static final String MESSAGE = "Role not found";
    public RoleNotFoundException() {
        super(MESSAGE);
    }

    public HttpStatus getStatus(){
      return HttpStatus.NOT_FOUND;
    }
}
