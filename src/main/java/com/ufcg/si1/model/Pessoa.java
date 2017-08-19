package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Pessoa")
@Table(name="tb_pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private Endereco endereco;

	public Pessoa() {
	}

	public Pessoa(String nome, String email, String rua, String uf, String cidade) {
		this.nome = nome;
		this.email = email;
		this.endereco = new Endereco(rua, uf, cidade);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRua() {
		return this.endereco.getRua();
	}

	public String getUf() {
		return this.endereco.getUf();
	}

	public String getCidade() {
		return this.endereco.getCidade();
	}

	public void setRua(String rua) {
		this.endereco.setRua(rua);
	}

	public void setCidade(String cidade) {
		this.endereco.setCidade(cidade);
	}

	public void setUf(String uf) {
		this.endereco.setUf(uf);
	}
}
