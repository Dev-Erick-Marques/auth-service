package com.dev.auth_service.exceptions;

import com.dev.auth_service.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorDTO> handleException(ClientErrorException ex, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), ex.getStatus(), request.getRequestURI());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> InvalidJsonException(HttpMessageNotReadableException ex, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO("Invalid json", HttpStatus.BAD_REQUEST, request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }
//    @ExceptionHandler(DatabaseInconsistencyException.class)
//    public ResponseEntity<ErrorDTO> handleDatabaseInconsistency(DatabaseInconsistencyException ex, HttpServletRequest request){
//        ErrorDTO error = new ErrorDTO("Internal server error. Please contact suport.", HttpStatus.INTERNAL_SERVER_ERROR,request.getRequestURI());
//        return ResponseEntity.internalServerError().body(error);
//    }
}
