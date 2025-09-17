package com.dev.auth_service.service;

import com.dev.auth_service.domain.models.Role;
import com.dev.auth_service.domain.repository.RoleRepository;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;
import com.dev.auth_service.exceptions.exceptions.ClientErrorException;
import com.dev.auth_service.domain.repository.UserRepository;
import com.dev.auth_service.security.TokenService;
import com.dev.auth_service.domain.models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private TokenService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String authenticate(UserRequestDTO dto){
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.generateToken(authentication);
    }

    @Transactional
    public UserResponseDTO register(UserRequestDTO dto){
        if (userRepository.existsByUserEmail(dto.email())){
            throw new ClientErrorException("Email already registered", HttpStatus.CONFLICT);
        }
            User user = new User(
                    dto.email(),
                    passwordEncoder.encode(dto.password()),
                    dto.username()
            );
            Role role = roleRepository.findByRoleName("ROLE_USER")
                    .orElseThrow(() -> new ClientErrorException("Role 'ROLE_USER' not found",
                            HttpStatus.NOT_FOUND));
            user.getRoles().add(role);
            return new UserResponseDTO(userRepository.save(user));

    }
}