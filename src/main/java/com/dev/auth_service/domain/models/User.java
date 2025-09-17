package com.dev.auth_service.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Setter
    private String userEmail;
    @Setter
    private String userPassword;
    @Setter
    private String username;

    @Column(nullable = false)
    private Boolean isDeleted = false;


    @CreatedDate
    @Column(name = "created_at",nullable = false,columnDefinition = "TIMESTAMP(3)")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false,columnDefinition = "TIMESTAMP(3)")
    private Instant updatedAt;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles",joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();


    public User(String userEmail, String userPassword, String username) {

        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.username = username;
    }
    public User(){}
}

