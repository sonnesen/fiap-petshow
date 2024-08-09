package com.petshow.petshow.dto;

// Record que encapsula a resposta de dados do usuário, contendo id, nome, email e senha
public record UserResponse(Long id, String name, String email, String password) {
}
