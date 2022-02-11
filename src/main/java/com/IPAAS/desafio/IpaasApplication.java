package com.IPAAS.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.IPAAS.desafio")
@EntityScan("com.IPAAS.desafio.model")
public class IpaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpaasApplication.class, args);
	}

}
