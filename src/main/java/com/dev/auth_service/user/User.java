package com.dev.auth_service.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String senha;

    public String getSenha() {
    return senha;
}
    public String getUsername() {
        return username;
}

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
