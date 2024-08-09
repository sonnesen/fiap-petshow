package com.petshow.petshow.dto;

// Record que encapsula a resposta de autenticação, contendo o token JWT gerado
public record AuthenticationResponse(String token) {
}
