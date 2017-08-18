package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Especialidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@Column
	private String descricao;

	public Especialidade(String descricao) {
		this.descricao = descricao;
	}

	public Especialidade() {
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getCodigo() {
		return this.ID;
	}

	public void setCodigo(long codigo) {
		this.ID = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Especialidade) {
			Especialidade esp = (Especialidade) obj;
			if (esp.getDescricao().equals(this.getDescricao()) && esp.getCodigo() == this.getCodigo())
				return true;
		}
		return false;
	}

}
