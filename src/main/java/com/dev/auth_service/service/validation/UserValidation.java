package com.dev.auth_service.service.validation;

import org.springframework.stereotype.Component;

import com.dev.auth_service.domain.models.Role;
import com.dev.auth_service.domain.repository.RoleRepository;
import com.dev.auth_service.domain.repository.UserRepository;
import com.dev.auth_service.exceptions.exceptions.EmailExistenteException;
import com.dev.auth_service.exceptions.exceptions.RoleNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserValidation {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public void validaSeEmailExiste(String email) {
		if (userRepository.existsByUserEmail(email)) {
			throw new EmailExistenteException();
		}
	}

	public Role validaSeRoleExiste(String role) {
		return roleRepository.findByRoleName(role).orElseThrow(RoleNaoEncontradaException::new);
	}
}
