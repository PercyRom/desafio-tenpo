package com.tenpo.desafio.app.exception;

public class InternalErrorExceptionHandler  extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InternalErrorExceptionHandler(String mensaje) {
        super(mensaje);
    }

}