package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.UserRegisterRequest;
import com.petshow.petshow.dto.UserResponse;
import com.petshow.petshow.entity.User;
import com.petshow.petshow.service.TokenService;
import com.petshow.petshow.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Endpoint para registro de usuário
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRegisterRequest userCreateRequest)
            throws MessagingException, UnsupportedEncodingException {

        // Converte o request de criação de usuário em um modelo de entidade User
        User user = userCreateRequest.toModel();

        // Chama o serviço para registrar o usuário no sistema e salvar os dados no banco
        UserResponse userSaved = userService.registerUser(user);

        // Retorna a resposta HTTP com status 200 (OK) e o usuário registrado no corpo da resposta
        return ResponseEntity.ok().body(userSaved);
    }

    // Endpoint para verificação de usuário através de um código enviado por e-mail
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        // Verifica o código enviado, se for válido retorna uma mensagem de sucesso
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            // Se o código não for válido, retorna uma mensagem de falha
            return "verify_fail";
        }
    }

    // Endpoint de teste para verificar se o usuário está logado
    @GetMapping("/teste")
    public String teste() {
        // Retorna uma mensagem simples indicando que o usuário está logado
        return "você está logado";
    }

}
