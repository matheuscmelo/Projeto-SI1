package com.ufcg.si1.model;

public class Prefeitura {
	
	EficienciaTemplate situacaoPrefeitura;
	
	public Prefeitura() {
		this.situacaoPrefeitura = new SituacaoNormal();
	}
	
	public int calculaEficiencia(int numeroQueixasAbertas, int numeroDeQueixas) {
		return this.situacaoPrefeitura.calculaEficiencia(numeroQueixasAbertas, numeroDeQueixas);
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
