package com.dev.auth_service.domain.repository;

import com.dev.auth_service.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
	
    Optional<User> findByUserEmail(String email);
    Optional<User> findByUsername(String email);
    boolean existsByUserEmail(String email);
    Boolean existsByUsername (String username);
}
