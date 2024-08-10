package com.petshow.petshow.infra.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TratErrosException {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    } //404

//    ===============================================================================================================

    // tratar erros de validação // mais de um por isto uma lista
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ItmInexistenteException.class)
    public ResponseEntity tratarError400Inesistente(ItmInexistenteException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    //     pega a minha exception e joga no corpo da requisição este erro
    @ExceptionHandler(ItemExistenteException.class)
    public ResponseEntity tratarError400Existe(ItemExistenteException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(OrderBloqException.class)
    public ResponseEntity tratarError400Inalterável(OrderBloqException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValorPagoInsuficienteException.class)
    public ResponseEntity tratarError400Insuficiente(ValorPagoInsuficienteException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) { // construtor para o map
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
