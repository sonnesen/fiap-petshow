package com.petshow.petshow.dto;

// Record que encapsula os dados de autenticação
public record AuthenticationRequest(String email, String password) {
}
