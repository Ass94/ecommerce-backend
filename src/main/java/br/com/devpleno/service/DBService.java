package br.com.devpleno.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpleno.domain.Categoria;
import br.com.devpleno.repositories.CategoriaRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void initializeTestDatabase() {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		
	}

}
