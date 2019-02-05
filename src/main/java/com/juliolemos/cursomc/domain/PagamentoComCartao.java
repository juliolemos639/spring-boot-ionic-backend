package com.juliolemos.cursomc.domain;

import javax.persistence.Entity;

import com.juliolemos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	// Atributos básicos
	private Integer numeroDeParcelas;
	
	// Construtor vazio
	public PagamentoComCartao() {
		
		
	}

	// Construtor com argumentos
	// Acrescentar nueroDeParcelas nos atributos
	public PagamentoComCartao(Integer ig, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(ig, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	// Getters e setters
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	

	
	
}
