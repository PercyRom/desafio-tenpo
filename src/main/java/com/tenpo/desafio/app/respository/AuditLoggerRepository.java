package com.tenpo.desafio.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenpo.desafio.app.entity.AuditLogger;

public interface AuditLoggerRepository extends JpaRepository<AuditLogger, Integer> {
	
	AuditLogger findTopByOrderByIdDesc();

}
