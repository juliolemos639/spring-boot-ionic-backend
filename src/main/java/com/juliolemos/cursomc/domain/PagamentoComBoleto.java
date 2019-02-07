package com.juliolemos.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juliolemos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	// Atributos básicos
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	// Construtor vazio
	public PagamentoComBoleto() {
		
	}
	// Construtor com argumentos
	// Como o pagamento com boleto é uma subclasse
	// Acrescentar dataVencimento e dataPagamento aos atributos

	public PagamentoComBoleto(Integer ig, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(ig, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	// Getters e setters
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
