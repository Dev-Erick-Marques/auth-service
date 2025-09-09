package com.dev.auth_service.exceptions;

import org.springframework.http.HttpStatus;

public class ClientErrorException extends RuntimeException {
  private final HttpStatus status;


  public ClientErrorException(String message, HttpStatus status) {
    super(message);
    if (!status.is4xxClientError()){
      throw new IllegalArgumentException("ClientErrorException accepts only 4xx errors ");
    }
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
