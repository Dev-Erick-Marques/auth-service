package com.dev.auth_service.service;

import com.dev.auth_service.dto.UserRequestDTO;
import com.dev.auth_service.dto.UserResponseDTO;

import java.util.UUID;

public interface UserService {
    void deleteUserById(UUID id);
    void restoreUserById(UUID id);
    UserResponseDTO register(UserRequestDTO dto);
    String authenticate(UserRequestDTO dto);

}
