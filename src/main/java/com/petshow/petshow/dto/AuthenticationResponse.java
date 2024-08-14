package com.petshow.petshow.dto;

import lombok.Builder;

// Record que encapsula a resposta de autenticação, contendo o token JWT gerado
@Builder
public record AuthenticationResponse(String token) {
}
