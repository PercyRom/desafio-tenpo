package com.tenpo.desafio.app.smoke;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.desafio.app.dto.SumaDto;

/*
 * El servicio externo puede ser un mock, tiene que devolver el % sumado.
 */
@RestController
@RequestMapping("/api/v1/smoke")
public class SmokeController {

	@PostMapping("/porcentaje")
	public ResponseEntity<?> create(@RequestBody SumaDto suma) {

		Map<String, Object> result = new LinkedHashMap<>();
		result.put("resultado", (suma.getNumero1() + suma.getNumero2()));

		Map<String, Object> data = new LinkedHashMap<>();

		data.put("status", 0);
		data.put("message", "OK");
		data.put("data", result);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
