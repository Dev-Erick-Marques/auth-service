package com.dev.auth_service.exceptions;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends RuntimeException {
    private final HttpStatus status;

  public ServerErrorException(String message, HttpStatus status) {
    super(message);
    if(!status.is5xxServerError()){
      throw new IllegalArgumentException("ServerErrorException only accepts 5xx errors");
    }
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
