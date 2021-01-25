package br.com.devpleno.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpleno.domain.Categoria;
import br.com.devpleno.domain.Cidade;
import br.com.devpleno.domain.Estado;
import br.com.devpleno.domain.Produto;
import br.com.devpleno.repositories.CategoriaRepository;
import br.com.devpleno.repositories.CidadeRepository;
import br.com.devpleno.repositories.EstadoRepository;
import br.com.devpleno.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public void initializeTestDatabase() {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto3));
		
		produto1.getCategorias().add(categoria1);
		produto2.getCategorias().add(categoria2);
		produto3.getCategorias().add(categoria1);
		
		Estado estado1 = new Estado(null, "São Paulo");
		Estado estado2 = new Estado(null, "Minas Gerais");
		
		Cidade cidade1 = new Cidade(null, "São Paulo", estado1);
		Cidade cidade2 = new Cidade(null, "Campinas", estado1);
		Cidade cidade3 = new Cidade(null, "Uberlândia", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade2));
		estado2.getCidades().add(cidade3);
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		
	}

}
