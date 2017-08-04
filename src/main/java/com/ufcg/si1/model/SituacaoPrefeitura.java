package com.ufcg.si1.model;

public class SituacaoPrefeitura {
	
	EficienciaTemplate situacaoPrefeitura;
	
	public SituacaoPrefeitura() {
		this.situacaoPrefeitura = new EficienciaImplNormal();
	}
	
	public int calculaSituacao(int numeroQueixasAbertas, int numeroDeQueixas) {
		return this.situacaoPrefeitura.calculaSituacao(numeroQueixasAbertas, numeroDeQueixas);
	}

	public void mudaParaCaos() {
		this.situacaoPrefeitura = new EficienciaImplCaos();
	}

	public void mudaParaExtra() {
		this.situacaoPrefeitura = new EficienciaImplExtra();
	}

	public void mudaParaNormal() {
		this.situacaoPrefeitura = new EficienciaImplNormal();
	}

}
