package com.petshow.petshow.service;

import com.petshow.petshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Implementa o método da interface UserDetailsService para carregar um usuário pelo nome de usuário (e-mail)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Usa o UserRepository para encontrar um usuário pelo e-mail
        UserDetails user = userRepository.findByEmail(username);

        // Se o usuário não for encontrado, lança uma exceção indicando que o usuário não foi encontrado
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username);
        }

        // Retorna o usuário encontrado, que deve implementar UserDetails
        return user;

    }

}