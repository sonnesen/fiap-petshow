package com.petshow.petshow.infra.exeption;

import java.util.UUID;

public class ItmInexistenteException extends Exception {
    /**
     *
     */
    private static final UUID serialVersionUID = UUID.randomUUID();

    public ItmInexistenteException(String msg) {
        super(msg);
    }
}