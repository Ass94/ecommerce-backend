package br.com.devpleno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.devpleno.service.DBService;

@Configuration
public class TestConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean initializate() {
		service.initializeTestDatabase();
		return true;
	}
	

}
