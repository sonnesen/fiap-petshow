package com.petshow.petshow.repository;

import com.petshow.petshow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Método para encontrar um usuário pelo e-mail
    UserDetails findByEmail(String email);

    // Método para encontrar um usuário pelo código de verificação
    User findByVerificationCode(String verificationCode);

}