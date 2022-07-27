package com.tenpo.desafio.app.exception;

import org.springframework.http.HttpStatus;

public class ValidationHttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Integer codigo;
	private final HttpStatus status;

	public ValidationHttpException(HttpStatus status, Integer codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.status = status;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
