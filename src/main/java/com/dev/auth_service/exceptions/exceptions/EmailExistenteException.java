package com.dev.auth_service.exceptions.exceptions;

public class EmailExistenteException extends RuntimeException{
	
	private static final String EMAIL_EXISTENTE = "Email already registered";

	public EmailExistenteException() {
		super(EMAIL_EXISTENTE);
	}
}
