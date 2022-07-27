package com.tenpo.desafio.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AuditLogger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero1")
	private Integer numero1;
	
	@Column(name = "numero2")
	private Integer numero2;
	
	@Column(name = "resultado")
	private Double resultado;
	
	@Column(name = "porcentaje")
	private Double porcentaje;
	
	@Column(name = "fraccion")
	private Double fraccion;

	@Column(name = "fechaRegistro")
	private LocalDateTime fechaRegistro;

}
