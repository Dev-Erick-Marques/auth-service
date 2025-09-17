package com.dev.auth_service.init;

import com.dev.auth_service.domain.models.Role;
import com.dev.auth_service.domain.repository.RoleRepository;
import com.dev.auth_service.domain.models.User;
import com.dev.auth_service.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer{
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner init(UserRepository userRepository,RoleRepository roleRepository){
        return args -> {
            List<String> roles = List.of("ROLE_SUPERADMIN","ROLE_ADMIN","ROLE_USER");
            roles.forEach(role -> roleRepository.findByRoleName(role)
                    .orElseGet(() -> roleRepository.save(new Role(role)))
            );
            if(!userRepository.existsByUsername("superadmin")){

                Role role = roleRepository.findByRoleName("ROLE_SUPERADMIN")
                        .orElseThrow(() -> new RuntimeException("Role not found."));
                User user = new User(
                        "superadmin@auth.com",
                        passwordEncoder.encode("qCLU4dYfj6IsLyK5fE8h"),
                        "superadmin"
                );
                user.getRoles().add(role);
                userRepository.findByUsername("superadmin")
                        .orElseGet(() -> userRepository.save(user));
            }

        };
    }
}
