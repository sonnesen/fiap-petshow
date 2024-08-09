package com.petshow.petshow.util;

import java.security.SecureRandom;


public class RandomString {

    // Conjunto de caracteres que serão usados para gerar a string aleatória
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Método para gerar uma string aleatória com um comprimento especificado
    public static String generateRandomString(int length) {
        // Cria uma instância de SecureRandom para gerar números aleatórios seguros
        SecureRandom secureRandom = new SecureRandom();

        // Usado para construir a string aleatória
        StringBuilder sb = new StringBuilder();

        // Preenche a StringBuilder com caracteres aleatórios
        for (int i = 0; i < length; i++) {
            // Gera um índice aleatório dentro do intervalo de caracteres disponíveis
            int index = secureRandom.nextInt(CHARACTERS.length());

            // Adiciona o caractere correspondente ao índice gerado à StringBuilder
            sb.append(CHARACTERS.charAt(index));
        }

        // Retorna a string aleatória gerada
        return sb.toString();
    }
}
