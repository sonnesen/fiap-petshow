package com.petshow.petshow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    // Configuração do filtro de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Desabilita a proteção CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                // Define a política de gerenciamento de sessão como STATELESS (sem estado)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura as autorizações para requisições HTTP
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("swagger-ui/**", "swagger-ui**", "/v3/api-docs/**", "/v3/api-docs**").permitAll()
                        // Permite o acesso sem autenticação para o endpoint POST /api/v1/user/register
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll()
                        // Permite o acesso sem autenticação para o endpoint GET /api/v1/user/verify
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/verify").permitAll()
                        // Permite o acesso sem autenticação para o endpoint POST /api/v1/auth/login
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        // Permite acesso à documentação do Swagger sem autenticação
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Exige autenticação para qualquer outra requisição
                                .requestMatchers(HttpMethod.GET, "/api/v1/user/verify").permitAll()
                        .anyRequest().permitAll()
                )

                // Adiciona o filtro de segurança customizado antes do filtro padrão UsernamePasswordAuthenticationFilter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Configuração do gerenciador de autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean para configurar o encoder de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
