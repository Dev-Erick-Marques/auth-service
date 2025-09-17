package com.dev.auth_service.domain.repository;

import com.dev.auth_service.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByRoleName(String name);
    Optional<Role> findByRoleName(String name);
}
