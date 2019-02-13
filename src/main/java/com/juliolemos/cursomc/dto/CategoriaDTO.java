package com.juliolemos.cursomc.dto;

import java.io.Serializable;

import com.juliolemos.cursomc.domain.Categoria;

// Implementa serializable para que fique fácil gravar em arquivo, trafegar em rede
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	// Construtor vazio
	public CategoriaDTO() {
		
	}
	
	// para importar a categoria
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	// Getters e setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
