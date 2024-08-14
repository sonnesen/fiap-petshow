package com.petshow.petshow.dto;

import lombok.Builder;

// Record que encapsula os dados de autenticação
@Builder
public record AuthenticationRequest(String email, String password) {
}
