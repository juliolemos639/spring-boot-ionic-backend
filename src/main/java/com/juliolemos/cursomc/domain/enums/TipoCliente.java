package com.juliolemos.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	// Construtor
	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		// Percorre todos os valores possíveis do meu enumerado cliente
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
			
		}
		throw new IllegalArgumentException("Id inválido " + cod);
		
	}
	
}
