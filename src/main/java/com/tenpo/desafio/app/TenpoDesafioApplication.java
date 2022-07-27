package com.tenpo.desafio.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRetry(proxyTargetClass=true) 
@EnableScheduling
public class TenpoDesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenpoDesafioApplication.class, args);
	}

}
