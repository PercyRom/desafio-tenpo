package com.tenpo.desafio.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import com.tenpo.desafio.app.entity.AuditLogger;

public interface AuditLoggerService {

	@Async
	public <S extends AuditLogger> S save(S entity);
	
	@Async
	public Page<AuditLogger> findAll(Pageable pageable);
	
	@Async
	public AuditLogger findTopByOrderByIdDesc();
}
