package com.dev.auth_service.controller;

import com.dev.auth_service.dto.TokenDTO;
import com.dev.auth_service.service.AuthenticationService;
import com.dev.auth_service.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return ResponseEntity.ok(new TokenDTO(authenticationService.authenticate(user)));
    }
}
