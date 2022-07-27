package com.tenpo.desafio.app.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tenpo.desafio.app.dto.OperacionRequest;
import com.tenpo.desafio.app.dto.OperacionResponse;
import com.tenpo.desafio.app.dto.ResponseObj;
import com.tenpo.desafio.app.helper.RetrieveHelper;
import com.tenpo.desafio.app.util.StringUtils;

@Component
public class RestCall {

	@Value("${dominioExterno}")
	private String dominioExterno;

	@Value("${servicioExterno}")
	private String pathServicioExterno;

	public ResponseObj<OperacionResponse> porcentaje(OperacionRequest request) {
		String uri = StringUtils.concatenarCadenas("/", dominioExterno, pathServicioExterno);
		uri = isFallo(request) ? uri.concat("/*") : uri;
		return RetrieveHelper.post(uri, request, OperacionResponse.class);
	}

	private static boolean isFallo(OperacionRequest request) {
		return (request.getNumero1() < 0 || request.getNumero2() < 0) ? true : false;
	}
}
