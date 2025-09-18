package com.dev.auth_service.exceptions.exceptions;

public class RoleNaoEncontradaException extends RuntimeException{
	
	private static final String ROLE_NAO_ENCONTRADA = "Role 'ROLE_USER' not found";
	
	public RoleNaoEncontradaException() {
		super(ROLE_NAO_ENCONTRADA);
	}

}
