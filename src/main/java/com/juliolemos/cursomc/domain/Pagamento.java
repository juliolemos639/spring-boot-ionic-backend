package com.juliolemos.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juliolemos.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Atributos básicos
	@Id
	private Integer ig;
	private Integer estado; // Tipo enumerado
	
	// Associação para um pedido
	@JsonIgnore  // Não permitir serialização
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId // para garantir que é o mesmo id do pedido
	private Pedido pedido;
	
	// Construtor Vazio
	public Pagamento() {
	}
	// Construtor com arqgumentos
	public Pagamento(Integer ig, EstadoPagamento estado, Pedido pedido) {
		super();
		this.ig = ig;
		this.estado = (estado==null) ? null : estado.getCod();
		this.pedido = pedido;
	}
	
	// Getters e setters
	public Integer getIg() {
		return ig;
	}
	public void setIg(Integer ig) {
		this.ig = ig;
	}
	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}
	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	// hasCode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ig == null) ? 0 : ig.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (ig == null) {
			if (other.ig != null)
				return false;
		} else if (!ig.equals(other.ig))
			return false;
		return true;
	}

	
	
}
