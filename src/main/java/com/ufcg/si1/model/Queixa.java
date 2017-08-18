package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Queixa")
@Table(name="tb_queixa")
public class Queixa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String descricao;
	@Column
	private Pessoa solicitante;
	@Column
	private SituacaoQueixa situacaoQueixa;
	@Column
	private String comentario = ""; // usado na atualizacao da queixa

	public Queixa() {
	}

	public Queixa(long id, String descricao, String comentario, String nome, String email, String rua, String uf,
			String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.situacaoQueixa = new QueixaAberta();
		this.comentario = comentario;
		this.solicitante = new Pessoa(nome, email, rua, uf, cidade);
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
