package br.com.devpleno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpleno.domain.Estado;
import br.com.devpleno.repositories.EstadoRepository;
import br.com.devpleno.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public Estado findById(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + " Tipo: " + Estado.class.getName()));
	}
	
	public List<Estado> findAll() {
		return repo.findAll();
	}

}
