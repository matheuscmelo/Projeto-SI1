package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="QueixaAnimal")
public class QueixaAnimal extends Queixa {
	@Column
	private String animal;
	@Column
	private String reclamacao;
	
	public QueixaAnimal() {
		super();
	}
	
	public QueixaAnimal(long id, String descricao, String comentario, String nome, String email, String rua,
			String uf, String cidade, String animal) {
		super(id,descricao,comentario,nome,email,rua,uf,cidade);
		this.animal = animal;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(String reclamacao) {
		this.reclamacao = reclamacao;
	}
	
	
}
