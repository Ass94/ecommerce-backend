package br.com.devpleno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpleno.domain.Cidade;
import br.com.devpleno.repositories.CidadeRepository;
import br.com.devpleno.service.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + " Tipo: " + Cidade.class.getName()));
	}
	
	public List<Cidade> findAll() {
		return repo.findAll();
	}

}
