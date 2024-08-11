package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.AuthenticationRequest;
import com.petshow.petshow.dto.AuthenticationResponse;
import com.petshow.petshow.entity.User;
import com.petshow.petshow.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    // Endpoint para login
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {

        // Cria um token de autenticação a partir do e-mail e senha fornecidos na requisição
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(), authenticationRequest.password()
        );

        // Autentica o usuário usando o AuthenticationManager
        var auth = authenticationManager.authenticate(usernamePassword);

        // Gera um token JWT para o usuário autenticado
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // Retorna o token JWT em uma resposta HTTP com status 200 (OK)
        return ResponseEntity.ok(new AuthenticationResponse(token));

    }

}