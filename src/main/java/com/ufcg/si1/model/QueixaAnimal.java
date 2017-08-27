package com.ufcg.si1.model;

public class QueixaAnimal extends Queixa{
	
	private String animal;
	
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
}
