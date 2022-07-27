package com.tenpo.desafio.app.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mensaje;

	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje() {
	}

}
