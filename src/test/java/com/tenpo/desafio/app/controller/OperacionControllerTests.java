package com.tenpo.desafio.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.desafio.app.dto.OperacionRequest;
import com.tenpo.desafio.app.dto.ResponseDto;
import com.tenpo.desafio.app.entity.AuditLogger;
import com.tenpo.desafio.app.secutiry.jwt.JwtEntryPoint;
import com.tenpo.desafio.app.secutiry.jwt.JwtProvider;
import com.tenpo.desafio.app.secutiry.service.UserDetailsServiceImpl;
import com.tenpo.desafio.app.service.AuditLoggerService;
import com.tenpo.desafio.app.service.OperacionService;

@DataJpaTest
@WebMvcTest(OperacionController.class)
class OperacionControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OperacionService operacionService;

	@MockBean
	private AuditLoggerService auditService;

	@MockBean
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@MockBean
	private JwtEntryPoint jwtEntryPoint;

	@MockBean
	private JwtProvider jwtProvider;

	@Test
	void operaciones_whenIngresaNumeros_obtenerSumaPorcentaje() throws Exception {

		ResponseDto response = new ResponseDto();
		response.setCodigo(0);
		response.setMensaje("OK");
		
		OperacionRequest request = new OperacionRequest(1, 1);
		
		when(operacionService.suma(request)).thenReturn(response);

		mockMvc.perform(post("/v1/operacion/suma")
		.content(new ObjectMapper().writeValueAsString(request))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void operaciones_whenIngresaPagina_obtenerListaRegistrosPaginados() throws Exception {
		
		PageRequest pageable =  PageRequest.of(0, 4);
		when(auditService.findAll(pageable)).thenReturn(new PageImpl<AuditLogger>(new ArrayList<>()));

		mockMvc.perform(get("/audit/page/{page}",0)
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

}
