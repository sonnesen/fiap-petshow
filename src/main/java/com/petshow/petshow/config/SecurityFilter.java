package com.petshow.petshow.config;

import com.petshow.petshow.repository.UserRepository;
import com.petshow.petshow.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    // Método principal que filtra as requisições HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Recupera o token do cabeçalho da requisição
        var token = this.recoverToken(request);

        // Se o token estiver presente e válido
        if (token != null){
            // Valida o token e obtém o "subject" (e-mail do usuário, por exemplo)
            var subject = tokenService.validateToken(token);

            // Busca o usuário no repositório pelo e-mail (subject)
            UserDetails user = userRepository.findByEmail(subject);

            // Cria um objeto de autenticação com os detalhes do usuário
            var authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );

            // Define a autenticação no contexto de segurança do Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua com a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    // Método que recupera o token do cabeçalho "Authorization" da requisição
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        // Se o cabeçalho de autorização não estiver presente, retorna null
        if(authHeader == null){
            return null;
        } else {
            // Remove o prefixo "Bearer " do token e retorna o token puro
            return authHeader.replace("Bearer ", "");
        }
    }
}