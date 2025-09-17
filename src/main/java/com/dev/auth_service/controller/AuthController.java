package com.dev.auth_service.controller;

import com.dev.auth_service.dto.TokenDTO;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;
import com.dev.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService authenticationService;
    @Autowired
    private UserService userService;

    public AuthController(UserService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(new TokenDTO(authenticationService.authenticate(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Validated UserRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(dto));
    }
}
