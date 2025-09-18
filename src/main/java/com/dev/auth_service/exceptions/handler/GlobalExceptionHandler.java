package com.dev.auth_service.exceptions.handler;

import com.dev.auth_service.exceptions.dto.ErroRespostaDto;
import com.dev.auth_service.exceptions.dto.ErrorDTO;
import com.dev.auth_service.exceptions.exceptions.ClientErrorException;
import com.dev.auth_service.exceptions.exceptions.EmailExistenteException;
import com.dev.auth_service.exceptions.exceptions.RoleNaoEncontradaException;

import com.dev.auth_service.exceptions.dto.ErrorListDTO;
import com.dev.auth_service.exceptions.dto.FieldErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public <T> ResponseEntity<T> buildResponse(HttpStatus status, T body){
        return ResponseEntity.status(status).body(body);

    }
    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorDTO> clientErrorHandler(ClientErrorException ex, HttpServletRequest request){
       return buildResponse(ex.getMessage(),ex.getStatus(),request.getRequestURI());

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> invalidJsonHandler(HttpMessageNotReadableException ex, HttpServletRequest request){
        return buildResponse("Invalid Json", HttpStatus.BAD_REQUEST, request.getRequestURI());

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorListDTO> fieldValidationHandler(MethodArgumentNotValidException ex, HttpServletRequest request){
        var errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(field -> new FieldErrorDTO(field.getField(), field.getDefaultMessage()))
                .toList();
        String message = "Validation error: some fields are missing or incorrect.";
        return errorListResponse(message, HttpStatus.BAD_REQUEST,errors, request.getRequestURI());

    }
    public ResponseEntity<ErrorDTO> buildResponse(String message, HttpStatus status, String path){
        ErrorDTO response = ErrorDTO.of(message,status,path);
        return buildResponse(status,response);

    }
    public ResponseEntity<ErrorListDTO> errorListResponse(String message, HttpStatus status, List<FieldErrorDTO> errors, String path){
        ErrorListDTO response = ErrorListDTO. of(message, status,errors, path);
        return buildResponse(status,response);
    }
    
    // Estrutura que ja cuida do status code e lança a mensagem de erro.
	@ExceptionHandler(EmailExistenteException.class)
	public ResponseEntity<ErroRespostaDto> handlerEmailExistente(EmailExistenteException ex, HttpServletRequest request){
		ErroRespostaDto erro = new ErroRespostaDto(
				ex.getMessage(), 
				HttpStatus.CONFLICT.value(), 
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(RoleNaoEncontradaException.class)
	public ResponseEntity<ErroRespostaDto> handlerRoleNaoEncontrada(RoleNaoEncontradaException ex, HttpServletRequest request){
		ErroRespostaDto erro = new ErroRespostaDto(
				ex.getMessage(), 
				HttpStatus.NOT_FOUND.value(), 
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
