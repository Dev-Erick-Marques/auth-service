package com.dev.auth_service.domain.repository;

import com.dev.auth_service.domain.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserEmail(String email);
    Optional<User> findByUsername(String email);
    Boolean existsByUserEmail(String email);
    Boolean existsByUsername (String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDeleted = :isDeleted WHERE u.userId = :id")
    void softDeleteById(UUID userId, boolean isDeleted);
}
