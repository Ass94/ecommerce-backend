package br.com.devpleno.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devpleno.domain.Categoria;
import br.com.devpleno.domain.Cidade;
import br.com.devpleno.domain.Cliente;
import br.com.devpleno.domain.Endereco;
import br.com.devpleno.domain.Estado;
import br.com.devpleno.domain.ItemPedido;
import br.com.devpleno.domain.Pagamento;
import br.com.devpleno.domain.PagamentoComBoleto;
import br.com.devpleno.domain.PagamentoComCartao;
import br.com.devpleno.domain.Pedido;
import br.com.devpleno.domain.Produto;
import br.com.devpleno.domain.enums.EstadoPagamento;
import br.com.devpleno.domain.enums.TipoCliente;
import br.com.devpleno.repositories.CategoriaRepository;
import br.com.devpleno.repositories.CidadeRepository;
import br.com.devpleno.repositories.ClienteRepository;
import br.com.devpleno.repositories.EnderecoRepository;
import br.com.devpleno.repositories.EstadoRepository;
import br.com.devpleno.repositories.ItemPedidoRepository;
import br.com.devpleno.repositories.PagamentoRepository;
import br.com.devpleno.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void initializeTestDatabase() throws ParseException {
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

		Cliente cliente1 = new Cliente(null, "Alex de Souza Silva", "alex94tu@gmail.com", "42588941298",
				TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().add("41311183");

		Cliente cliente2 = new Cliente(null, "Matheus da Silva", "alex94tu@gmail.com", "28178001098",
				TipoCliente.PESSOAFISICA);
		cliente2.getTelefones().add("41311520");

		Endereco endereco1 = new Endereco(null, "Rua Jasmiro Teixeira", "86", null, "Jardim Bom Jesus", "06550000",
				cidade1, cliente1);
		Endereco endereco2 = new Endereco(null, "Rua Castro Alves", "59", "Casa 4", "Centro", "13010070", cidade2,
				cliente2);
		Endereco endereco3 = new Endereco(null, "Rua Afonso Magalhães", "288", null, "Centro", "35680435", cidade3,
				cliente1);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		cliente2.getEnderecos().add(endereco3);

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, df.parse("27/01/2021 10:00"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, df.parse("27/01/2021 10:00"), cliente2, endereco3);
		
		ItemPedido item1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);

		pedido1.getItens().addAll(Arrays.asList(item1, item2));
		pedido2.getItens().add(item3);

		produto1.getItens().add(item1);
		produto2.getItens().add(item3);
		produto3.getItens().add(item2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				df.parse("30/01/2021 00:00"), null);

		pedido1.setPagamento(pagamento1);
		pedido2.setPagamento(pagamento2);

		

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3));
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

	}

}
