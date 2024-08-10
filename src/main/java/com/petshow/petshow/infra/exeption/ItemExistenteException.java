package com.petshow.petshow.infra.exeption;

import java.util.UUID;

public class ItemExistenteException extends Exception {
    /**
     *
     */
    private static final UUID serialVersionUID = UUID.randomUUID();

    public ItemExistenteException(String msg) {
        super(msg);
    }
}