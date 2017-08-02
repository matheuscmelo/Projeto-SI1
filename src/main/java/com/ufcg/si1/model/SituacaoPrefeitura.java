package com.ufcg.si1.model;

public class SituacaoPrefeitura {
	
	IFSituacaoPrefeitura situacaoPrefeitura;
	
	public SituacaoPrefeitura() {
		this.situacaoPrefeitura = new SituacaoNormal();
	}
	
	public int changeSituation(int numeroQueixasAbertas, int numeroDeQueixas) {
		return situacaoPrefeitura.calculaSituacao(numeroQueixasAbertas, numeroDeQueixas);
	}

}
