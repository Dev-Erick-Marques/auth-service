package com.dev.auth_service.exceptions.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientErrorException extends RuntimeException {
  private final HttpStatus status;


  public ClientErrorException(String message, HttpStatus status) {
    super(message);
    if (!status.is4xxClientError()){
      throw new IllegalArgumentException("ClientErrorException accepts only 4xx errors ");
    }
    this.status = status;
  }

  @Getter
  public static class ServerErrorException extends RuntimeException {
      private final HttpStatus status;

    public ServerErrorException(String message, HttpStatus status) {
      super(message);
      if(!status.is5xxServerError()){
        throw new IllegalArgumentException("ServerErrorException only accepts 5xx errors");
      }
      this.status = status;
    }

  }
}
