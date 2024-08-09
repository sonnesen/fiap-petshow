package com.petshow.petshow.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.petshow.petshow.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret; // A chave secreta para assinar e verificar tokens JWT, injetada a partir do arquivo de propriedades

    // Método para gerar um token JWT para um usuário
    public String generateToken(User user) {
        try {
            // Cria um algoritmo de assinatura HMAC usando a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Cria e assina um novo token JWT
            String token = JWT.create()
                    .withIssuer("auth") // Define o emissor do token
                    .withSubject(user.getEmail()) // Define o assunto do token como o e-mail do usuário
                    .withExpiresAt(ExpirationDate()) // Define a data de expiração do token
                    .sign(algorithm); // Assina o token usando o algoritmo HMAC

            return token; // Retorna o token gerado
        } catch (JWTCreationException exception) {
            // Lança uma exceção em caso de falha na criação do token
            throw new RuntimeException("ERRO: Token não foi gerado", exception);
        }
    }

    // Método para validar um token JWT e retornar o sujeito (e-mail do usuário)
    public String validateToken(String token) {
        try {
            // Cria um algoritmo de assinatura HMAC usando a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verifica o token usando o algoritmo e o emissor esperado
            return JWT.require(algorithm)
                    .withIssuer("auth") // Verifica o emissor do token
                    .build()
                    .verify(token) // Verifica o token
                    .getSubject(); // Retorna o sujeito do token (e-mail do usuário)
        } catch (JWTVerificationException exception) {
            // Lança uma exceção em caso de falha na verificação do token
            throw new RuntimeException("Token inválido");
        }
    }

    // Método privado para calcular a data de expiração do token
    private Instant ExpirationDate() {
        // Define a data de expiração como 5 minutos a partir do momento atual
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-03:00"));
    }
}

