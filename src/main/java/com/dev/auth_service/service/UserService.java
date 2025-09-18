package com.dev.auth_service.service;

import com.dev.auth_service.domain.models.Role;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;
import com.dev.auth_service.domain.repository.UserRepository;
import com.dev.auth_service.security.TokenService;
import com.dev.auth_service.service.validation.UserValidation;
import com.dev.auth_service.domain.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

	private final TokenService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final UserValidation userValidation;

	public String authenticate(UserRequestDTO dto) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
		Authentication authentication = authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtService.generateToken(authentication);
	}

	@Transactional
	public UserResponseDTO register(UserRequestDTO dto) {
		userValidation.validaSeEmailExiste(dto.email());
		User user = new User(dto.email(), passwordEncoder.encode(dto.password()), dto.username());
		Role role = userValidation.validaSeRoleExiste("ROLE_USER");
		user.getRoles().add(role);
		return new UserResponseDTO(userRepository.save(user));
	}
}