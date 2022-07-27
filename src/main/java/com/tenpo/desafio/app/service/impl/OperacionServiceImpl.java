package com.tenpo.desafio.app.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tenpo.desafio.app.constant.Constant;
import com.tenpo.desafio.app.dto.OperacionRequest;
import com.tenpo.desafio.app.dto.OperacionResponse;
import com.tenpo.desafio.app.dto.ResponseDto;
import com.tenpo.desafio.app.dto.ResponseObj;
import com.tenpo.desafio.app.entity.AuditLogger;
import com.tenpo.desafio.app.exception.InternalErrorExceptionHandler;
import com.tenpo.desafio.app.rest.RestCall;
import com.tenpo.desafio.app.service.AuditLoggerService;
import com.tenpo.desafio.app.service.OperacionService;
import com.tenpo.desafio.app.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	private RestCall restCall;

	@Autowired
	private AuditLoggerService auditLoggerService;

	/*
	 * Si el servicio externo falla, se puede reintentar hasta 3 veces.
	 */
	@Override
	@Retryable(value = { InternalErrorExceptionHandler.class }, maxAttempts = 3, backoff = @Backoff(5000))
	public ResponseDto suma(OperacionRequest request) {

		ResponseObj<OperacionResponse> responseObj = null;

		try {

			responseObj = restCall.porcentaje(request);

		} catch (InternalErrorExceptionHandler errorExceptionHandler) {
			throw errorExceptionHandler;
		}

		Double resultado = responseObj.getData().getResultado();

		Double fraccion = (double) (resultado / 100);
		Double respuesta = (fraccion * resultado) + (resultado);
		responseObj.getData().setResultado(respuesta);

		AuditLogger audit = new AuditLogger();
		audit.setNumero1(request.getNumero1());
		audit.setNumero2(request.getNumero2());
		audit.setPorcentaje(resultado);
		audit.setFraccion(fraccion);
		audit.setResultado(respuesta);
		audit.setFechaRegistro(LocalDateTime.now());

		auditLoggerService.save(audit);

		return ResponseUtils.evalResponse(responseObj);
	}

	/*
	 * Si el servicio externo falla, se debe devolver el último valor retornado.
	 * Si no hay valor, debe retornar un error la api.
	 */
	@Recover
	public ResponseDto recover(RuntimeException t, OperacionRequest request) {
		log.info(Constant.MSG_RETRY_RECOVERY, t.getMessage());
		
		ResponseObj<OperacionResponse> responseObj = new ResponseObj<>();
		OperacionResponse response = new OperacionResponse();
		
		Optional<AuditLogger> audit = Optional.ofNullable(auditLoggerService.findTopByOrderByIdDesc());
		if(audit.isPresent()) {
			response.setResultado(audit.get().getResultado());
			
			responseObj.setMessage(Constant.DATA_OK);
			responseObj.setStatus(Constant.COD_OK);
			responseObj.setData(response);
		}else {
			AuditLogger auditLogger =  new AuditLogger();
			auditLogger.setResultado(0.0);
			
			response.setResultado(auditLogger.getResultado());
			
			responseObj.setMessage(Constant.DATA_EMPTY);
			responseObj.setStatus(Constant.COD_ERR);
			responseObj.setData(response);
		}
		return ResponseUtils.evalResponse(responseObj);
	}

	/*
	 * Dado que ese % varía poco, debe ser consumido cada media hora.
	 */
	@Override
	@Scheduled(fixedDelay = 1800000)
	public void monitorService() {
		ResponseObj<OperacionResponse> resp = restCall.porcentaje(new OperacionRequest(0, 0));
		log.info(Constant.MSG_MONITOREO, resp.getData().getResultado(), LocalDateTime.now());
	}

}
