package com.petshow.petshow.dto;

import com.petshow.petshow.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest (
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        @Schema(description = "The name of the user", example = "John Doe")
        String name,

        @NotNull(message = "Email não pode ser nulo")
        @NotBlank(message = "Email não pode ser vazio")
        @Email
        @Schema(description = "The email address of the user", example = "john.doe@example.com")
        String email,

        @NotNull(message = "Senha não pode ser nula")
        @NotBlank(message = "Senha não pode ser vazia")
        @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
        @Schema(description = "The password for the user", example = "password123")
        String password,

        @NotNull(message = "Senha não pode ser nula")
        @NotBlank(message = "Senha não pode ser vazia")
        @Schema(description = "The role of the user", example = "USER")
        String role) {

    // Método que converte UserRegisterRequest em um objeto User
    public User toModel(){
        return new User(name, email, password, role);
    } // Preenche o objeto User com as informações fornecidas no request

}