package com.tenpo.desafio.app.service;

import com.tenpo.desafio.app.dto.OperacionRequest;
import com.tenpo.desafio.app.dto.ResponseDto;

public interface OperacionService {

	ResponseDto suma(OperacionRequest request);
	
	void monitorService();
}
