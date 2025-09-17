package com.dev.auth_service.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
@Entity
@Getter
@Table(name = "roles")
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role(){}
}
