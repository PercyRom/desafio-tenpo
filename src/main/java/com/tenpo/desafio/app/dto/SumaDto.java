package com.tenpo.desafio.app.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SumaDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int numero1;
	private int numero2;
}
