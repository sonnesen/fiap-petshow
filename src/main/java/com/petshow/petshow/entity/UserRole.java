package com.petshow.petshow.entity;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    // Campo que armazena o nome da função (role)
    private String role;

    // Construtor da enumeração que associa uma string ao enum
    UserRole(String role) {
        this.role = role;
    }

    // Método que retorna o nome da função associada ao enum
    public String getRole() {
        return role;
    }
}
