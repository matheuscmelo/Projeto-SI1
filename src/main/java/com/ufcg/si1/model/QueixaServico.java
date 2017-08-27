package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="QueixaServico")
public class QueixaServico extends Queixa{
	@Column
	private String reclamacao;
	
	public QueixaServico() {
		super();
	}
	
	public QueixaServico(long id, String descricao, String comentario, String nome, String email, String rua,
			String uf, String cidade, String reclamacao) {
		super(id,descricao,comentario,nome,email,rua,uf,cidade);
		this.reclamacao = reclamacao;
	}

	public String getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(String reclamacao) {
		this.reclamacao = reclamacao;
	}
}
