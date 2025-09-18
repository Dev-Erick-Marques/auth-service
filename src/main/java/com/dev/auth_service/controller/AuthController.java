package com.dev.auth_service.controller;

import com.dev.auth_service.dto.TokenDTO;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;
import com.dev.auth_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService authenticationService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserRequestDTO dto){
        return ResponseEntity.ok(new TokenDTO(authenticationService.authenticate(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO dto){
    	UserResponseDTO userCreated = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
