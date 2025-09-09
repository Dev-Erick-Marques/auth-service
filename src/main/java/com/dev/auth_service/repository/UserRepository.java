package com.dev.auth_service.repository;

import com.dev.auth_service.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserEmail(String email);
    Boolean existsByUserEmail(String email);
}
