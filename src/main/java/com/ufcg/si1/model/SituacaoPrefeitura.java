package com.ufcg.si1.model;

public class SituacaoPrefeitura {
	
	IFSituacaoPrefeitura situacaoPrefeitura;
	
	public SituacaoPrefeitura() {
		this.situacaoPrefeitura = new SituacaoNormal();
	}
	
	public int calculaSituacao(int numeroQueixasAbertas, int numeroDeQueixas) {
		return this.situacaoPrefeitura.calculaSituacao(numeroQueixasAbertas, numeroDeQueixas);
	}

	public void mudaParaCaos() {
		this.situacaoPrefeitura = new SituacaoCaos();
	}

	public void mudaParaExtra() {
		this.situacaoPrefeitura = new SituacaoExtra();
	}

	public void mudaParaNormal() {
		this.situacaoPrefeitura = new SituacaoNormal();
	}

}
