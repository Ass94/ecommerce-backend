package br.com.devpleno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.devpleno.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean initializate() {
		service.initializeTestDatabase();
		return true;
	}
	

}
