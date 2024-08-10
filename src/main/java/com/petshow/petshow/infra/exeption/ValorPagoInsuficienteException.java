package com.petshow.petshow.infra.exeption;

import java.util.UUID;

public class ValorPagoInsuficienteException extends Exception {
    /**
     *
     */
    private static final UUID serialVersionUID = UUID.randomUUID();

    public ValorPagoInsuficienteException(String msg) {
        super(msg);
    }


    //package grafica.pedidos.api.infra.exeption;
    //
    //public class ValorPagoInsuficienteException extends Exception {
    //	/**
    //	 *
    //	 */
    //	private static final long serialVersionUID = 1L;
    //
    //	public ValorPagoInsuficienteException(String msg) {
    //		super(msg);
    //	}
    //}
}
