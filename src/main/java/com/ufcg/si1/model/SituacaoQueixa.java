package com.ufcg.si1.model;

public enum SituacaoQueixa {
	ABERTA(0),
	EM_ANDAMENTO(1),
	FECHADA(2);
	
	private int situacao;
	
	private SituacaoQueixa(int num) {
		this.situacao = num;
	}
	
	public int getSituacao() {
		return situacao;
	}
}
