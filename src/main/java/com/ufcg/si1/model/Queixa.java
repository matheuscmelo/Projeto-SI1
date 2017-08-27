package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_queixa")
public abstract class Queixa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String descricao;
	@OneToOne
	private Pessoa solicitante;
	@OneToOne
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@JsonIgnore
	private SituacaoQueixa situacaoQueixa = new QueixaAberta();;
	@Column
	private String comentario;
	@OneToOne
	private Endereco endereco;

	public Queixa() {}

	public Queixa(long id, String descricao, String comentario, String nome, String email, String rua, String uf,
			String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.situacaoQueixa = new QueixaAberta();
		this.comentario = comentario;
		this.solicitante = new Pessoa(nome, email);
		this.endereco = new Endereco(rua, uf, cidade);
	}
	
	public SituacaoQueixa getSituacaoQueixa() {
		return situacaoQueixa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SituacaoQueixa getSituacao() {
		return situacaoQueixa;
	}
	@JsonIgnore
	public void setSituacao(SituacaoQueixa situacao) {
		this.situacaoQueixa = situacao;
	}

	public void emAndamento() throws ObjetoInvalidoException {
		this.situacaoQueixa.emAndamento(this);
	}

	public void fechar(String coment) throws ObjetoInvalidoException {
		this.situacaoQueixa.fechar(this, coment);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
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
	
	public boolean isAberta() {
		return this.situacaoQueixa.isAberta();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Queixa other = (Queixa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
