package com.juliolemos.cursomc.resources.exception;

import java.io.Serializable;
// Formatabdo o retorno da mensagem
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	// Construtor vazio
	public FieldMessage() {
		
	}

	// Construtor com atributos
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	// Getters e setters
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
