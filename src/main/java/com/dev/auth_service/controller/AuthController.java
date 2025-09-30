package com.dev.auth_service.controller;

import com.dev.auth_service.dto.TokenDTO;
import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;
import com.dev.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserRequestDTO dto){
        return ResponseEntity.ok(new TokenDTO(userService.authenticate(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Validated UserRequestDTO dto){
        UserResponseDTO userCreated = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/users/{id}/restore")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> restoreUser(@PathVariable UUID id){
        userService.restoreUserById(id);
        return ResponseEntity.noContent().build();
    }
}
