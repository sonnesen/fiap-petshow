package com.petshow.petshow.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String verificationCode;

    private boolean enabled;

    private String role;

    // Construtor que inicializa todos os campos
    public User(Long id, String name, String email, String password, String verificationCode, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    // Construtor utilizado para criação de um novo usuário (não precisa do código de verificação nem do enabled)
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Construtor padrão sem argumentos, necessário para JPA
    public User() {
    }

    // Método para retornar as autoridades (permissões) do usuário - ainda não implementado
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    // Método que retorna o e-mail como o nome de usuário
    @Override
    public String getUsername() {
        return email;
    }

    // Indica que a conta do usuário não está expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica que a conta do usuário não está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica que as credenciais do usuário (senha) não estão expiradas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica se a conta do usuário está habilitada
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
