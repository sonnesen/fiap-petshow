package com.petshow.petshow.service;

import com.petshow.petshow.dto.UserResponse;
import com.petshow.petshow.entity.User;
import com.petshow.petshow.repository.UserRepository;
import com.petshow.petshow.util.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Injeta o repositório de usuários para acessar dados do banco

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeta o codificador de senha para criptografar senhas

    @Autowired
    private MailService mailService; // Injeta o serviço de e-mail para enviar e-mails

    // Método para registrar um novo usuário
    public UserResponse registerUser(User user) throws MessagingException, UnsupportedEncodingException {

        // Verifica se já existe um usuário com o mesmo e-mail
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists"); // Lança uma exceção se o e-mail já estiver em uso
        } else {
            // Criptografa a senha do usuário antes de salvar
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // Gera um código de verificação aleatório e define o usuário como não habilitado
            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            // Salva o usuário no banco de dados
            User savedUser = userRepository.save(user);

            // Cria uma resposta com as informações do usuário salvo
            UserResponse userResponse = new UserResponse(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPassword()
            );

            // Envia um e-mail de verificação para o usuário
            mailService.sendVerificationEmail(user);

            // Retorna a resposta do usuário
            return userResponse;
        }

    }

    // Método para verificar o código de verificação e ativar a conta do usuário
    public boolean verify(String verificationCode) {

        // Encontra o usuário pelo código de verificação
        User user = userRepository.findByVerificationCode(verificationCode);

        // Se o usuário não for encontrado ou já estiver habilitado, retorna false
        if(user == null || user.isEnabled()){
            return false;
        } else {
            // Limpa o código de verificação e habilita a conta do usuário
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            // Retorna true indicando que a verificação foi bem-sucedida
            return true;
        }

    }

}