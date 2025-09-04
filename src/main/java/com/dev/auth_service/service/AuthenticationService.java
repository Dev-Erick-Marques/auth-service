package com.dev.auth_service.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final TokenService jwtService;

    public AuthenticationService(TokenService jwtService) {
        this.jwtService = jwtService;

    }
    public String authenticate(Authentication authentication){
        return jwtService.generationToken(authentication);
    }
}