package com.petshow.petshow.infra.exeption;

public class OrderBloqException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public OrderBloqException(String msg) {
        super(msg);
    }
}