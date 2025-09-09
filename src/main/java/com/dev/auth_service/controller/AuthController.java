package com.dev.auth_service.controller;

import com.dev.auth_service.dto.TokenDTO;
import com.dev.auth_service.dto.UserDTO;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService authenticationService;
    @Autowired
    private UserService userService;

    public AuthController(UserService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO dto){
        return ResponseEntity.ok(new TokenDTO(authenticationService.authenticate(dto)));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserRequestDTO> register(@RequestBody @Validated UserDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(dto));
    }
}
