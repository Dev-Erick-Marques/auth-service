package com.dev.auth_service.service;

import com.dev.auth_service.dto.UserDTO;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.exceptions.ClientErrorException;
import com.dev.auth_service.exceptions.DatabaseInconsistencyException;
import com.dev.auth_service.exceptions.ServerErrorException;
import com.dev.auth_service.repository.UserRepository;
import com.dev.auth_service.security.jwt.TokenService;
import com.dev.auth_service.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private TokenService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String authenticate(UserDTO dto){
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        Authentication authentication = authenticationManager.authenticate(auth);

        return jwtService.generateToken(auth, getUserId(dto.email()));
    }
    public String getUserId(String email){
        Optional<String> uuid = userRepository.findByUserEmail(email)
                .map(User::getUserId)
                .map(String::valueOf);
        return uuid.orElse("");
    }

    @Transactional
    public UserRequestDTO register(UserDTO dto){
        if (userRepository.existsByUserEmail(dto.email())){
            throw new ClientErrorException("Email already registered", HttpStatus.CONFLICT);
        }
        try {
            User user = new User();
            user.setUserPassword(passwordEncoder.encode(dto.password()));
            user.setUserEmail(dto.email());
            return new UserRequestDTO(userRepository.save(user));
        } catch (Exception e) {
            throw new ServerErrorException("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}