package com.tenpo.desafio.app.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenpo.desafio.app.entity.AuditLogger;
import com.tenpo.desafio.app.respository.AuditLoggerRepository;
import com.tenpo.desafio.app.service.AuditLoggerService;

@Service
public class AuditLoggerServiceImpl implements AuditLoggerService {

	@Autowired
	AuditLoggerRepository repository;

	@Override
	public <S extends AuditLogger> S save(S entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AuditLogger> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public AuditLogger findTopByOrderByIdDesc() {
		return repository.findTopByOrderByIdDesc();
	}
	

}
