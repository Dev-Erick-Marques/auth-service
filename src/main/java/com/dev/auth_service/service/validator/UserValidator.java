package com.dev.auth_service.service.validator;

import com.dev.auth_service.domain.models.Role;
import com.dev.auth_service.domain.repository.RoleRepository;
import com.dev.auth_service.domain.repository.UserRepository;
import com.dev.auth_service.exceptions.exceptions.EmailNotFoundException;
import com.dev.auth_service.exceptions.exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public void validateUser(String email){
        if (userRepository.existsByUserEmail(email)){
            throw new EmailNotFoundException();
        }
    }
    public Role validateRole(String role){
        return roleRepository.findByRoleName(role)
                .orElseThrow(RoleNotFoundException::new);
    }
}
