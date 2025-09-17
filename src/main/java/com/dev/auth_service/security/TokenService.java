package com.dev.auth_service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final Instant now = Instant.now();

    private Long expiry = 1000*60L;


    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication){
        UserDetailsImpl datails = (UserDetailsImpl) authentication.getPrincipal();

        var claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(datails.getUserId().toString())
                .claim("scope",datails.getAuthorities())
                .build();
    return  encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }



}
