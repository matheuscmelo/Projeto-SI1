package com.ufcg.si1.model;

public class Especialidade {

	private long codigo;

	private String descricao;

	public Especialidade(String descricao) {
		this.codigo = 0; // gerado no repositorio
		this.descricao = descricao;
	}

	public Especialidade(String descricao, long codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Especialidade) {
			Especialidade esp = (Especialidade) obj;
			if (esp.getDescricao().equals(this.getDescricao()) 
					&& esp.getCodigo() == this.getCodigo())
				return true;
		}
		return false;
	}

}
