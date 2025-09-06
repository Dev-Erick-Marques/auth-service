package com.dev.auth_service.service;

import com.dev.auth_service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final TokenService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationService(TokenService jwtService){
        this.jwtService = jwtService;
    }
    public String authenticate(User user){
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getSenha());
        Authentication authentication = authenticationManager.authenticate(auth);

        return jwtService.generationToken(auth);
    }
}