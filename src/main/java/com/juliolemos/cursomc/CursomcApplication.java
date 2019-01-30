package com.juliolemos.cursomc;

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
import com.juliolemos.cursomc.domain.Produto;
import com.juliolemos.cursomc.domain.enums.TipoCliente;
import com.juliolemos.cursomc.repositories.CategoriaRepository;
import com.juliolemos.cursomc.repositories.CidadeRepository;
import com.juliolemos.cursomc.repositories.ClienteRepository;
import com.juliolemos.cursomc.repositories.EnderecoRepository;
import com.juliolemos.cursomc.repositories.EstadoRepository;
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
	
	// Principal
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Instanciando Categoria
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		// Instanciando Produto
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
				
	}

}

