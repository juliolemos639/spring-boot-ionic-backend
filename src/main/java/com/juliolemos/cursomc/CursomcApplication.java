package com.juliolemos.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.juliolemos.cursomc.domain.Categoria;
import com.juliolemos.cursomc.domain.Cidade;
import com.juliolemos.cursomc.domain.Cliente;
import com.juliolemos.cursomc.domain.Endereco;
import com.juliolemos.cursomc.domain.Estado;
import com.juliolemos.cursomc.domain.ItemPedido;
import com.juliolemos.cursomc.domain.Pagamento;
import com.juliolemos.cursomc.domain.PagamentoComBoleto;
import com.juliolemos.cursomc.domain.PagamentoComCartao;
import com.juliolemos.cursomc.domain.Pedido;
import com.juliolemos.cursomc.domain.Produto;
import com.juliolemos.cursomc.domain.enums.EstadoPagamento;
import com.juliolemos.cursomc.domain.enums.TipoCliente;
import com.juliolemos.cursomc.repositories.CategoriaRepository;
import com.juliolemos.cursomc.repositories.CidadeRepository;
import com.juliolemos.cursomc.repositories.ClienteRepository;
import com.juliolemos.cursomc.repositories.EnderecoRepository;
import com.juliolemos.cursomc.repositories.EstadoRepository;
import com.juliolemos.cursomc.repositories.ItemPedidoRepository;
import com.juliolemos.cursomc.repositories.PagamentoRepository;
import com.juliolemos.cursomc.repositories.PedidoRepository;
import com.juliolemos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	// Repositories
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
	
	// Principal
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Instanciando Categoria
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		// Instanciando Produto
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		// Instanciando Estado
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
				
		// Instanciando Cidade
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
			
		// Fazer com que o Estado reconheça as cidades
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		// Criar o Repository no pacote de repositories
		
		// Salvar os Estados e as cidades nos Repositórios
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		// Instanciação de Cidade, Endereco e Cliente
		// Endereco fica por último porque depende de cliente e Cidade
		// Ciente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		// Telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		// Endereços
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		// O cliente tem que conhecer os endereços dele
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		// Salvar cliente que dependente de endereco
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		// Instanciação dos pedidos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("03/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		// Instanciar pagamentos
		//
		// Pagamento com Cartão
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); // o pagamento do pedido 1 é o pagto1
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
		null);
		ped2.setPagamento(pagto2);
		
		// Associar o cliente cli1 com os pedidos dele
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		// Criar os repositories fisicamente e depois as dependências na parte superior (@Autowired)
		// salvar promeiro os pedidos, pois são independentes
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		// Instanciar Itens de pedidos
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		// Acessar o conjuntinho de ítens
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		// Produto conhecer os seus ítens
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		// Salvar na base de dados - Criar os repositories
		// depois...
		// Salvar os itens, mas antes colocar as dependências no @Wired
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
				
		
	}

}

