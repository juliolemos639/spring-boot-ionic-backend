package com.juliolemos.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	private Integer cod;
	private String descricao;
	
	// Construtor
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
// Get
	
	public int getCod() {
		return cod;
		
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Passa um código para receber a descrição
	// Static para ser possível de executada mesmo sem instanciar objetos
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		// Percorre todos os valores possíveis do meu enumerado cliente
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
			
		}
		throw new IllegalArgumentException("Id inválido " + cod);
		
	}
}
