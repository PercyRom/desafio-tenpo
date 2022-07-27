package com.tenpo.desafio.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.desafio.app.dto.OperacionRequest;
import com.tenpo.desafio.app.dto.ResponseDto;
import com.tenpo.desafio.app.entity.AuditLogger;
import com.tenpo.desafio.app.service.AuditLoggerService;
import com.tenpo.desafio.app.service.OperacionService;

@RestController
@RequestMapping("v1/operacion")
@CrossOrigin(origins = "*")
public class OperacionController {

	@Autowired
	OperacionService operacionService;

	@Autowired
	AuditLoggerService auditService;

	/*
	 * c. Debe contener un servicio llamado por api-rest que reciba 2 números, los
	 * sume, y le aplique una suba de un porcentaje que debe ser adquirido de un
	 * servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el
	 * porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). 
	 * Se deben tener en cuenta las siguientes consideraciones:
	 * 
	 * - El servicio externo puede ser un mock, tiene que devolver el % sumado. 
	 * - Dado que ese % varía poco, debe ser consumido cada media hora. 
	 * - Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la api. 
	 * - Si el servicio externo falla, se puede reintentar hasta 3 veces.
	 */
	@PostMapping("/suma")
	public ResponseEntity<ResponseDto> suma(@RequestBody OperacionRequest request) {
		return ResponseEntity.ok(operacionService.suma(request));
	}

	@GetMapping("/audit/page/{page}")
	public Page<AuditLogger> audit(@PathVariable Integer page) {
		return auditService.findAll(PageRequest.of(page, 4));
	}

}
