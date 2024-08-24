package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.AuthenticationRequest;
import com.petshow.petshow.dto.AuthenticationResponse;
import com.petshow.petshow.entity.User;
import com.petshow.petshow.exception.UserAlreadyExistsException;
import com.petshow.petshow.exception.UserNotFoundException;
import com.petshow.petshow.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("petshow/api/v1/auth")
@Tag(name = "petshow/api/v1/auth", description = "Controller responsável por gerenciar o login do usuário.")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}