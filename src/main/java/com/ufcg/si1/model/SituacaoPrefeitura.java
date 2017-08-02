package com.ufcg.si1.model;

public class SituacaoPrefeitura {
	
	IFSituacaoPrefeitura situacaoPrefeitura;
	
	public SituacaoPrefeitura() {
		this.situacaoPrefeitura = new SituacaoNormal();
	}
	
	public int calculaSituacao(int numeroQueixasAbertas, int numeroDeQueixas) {
		return this.situacaoPrefeitura.calculaSituacao(numeroQueixasAbertas, numeroDeQueixas);
	}
	
	public void trocaSituacao(int situacao) {
		if(situacao == 0) {
			this.situacaoPrefeitura = new SituacaoNormal();
		}else if(situacao == 1){
			this.situacaoPrefeitura = new SituacaoExtra();
		}else if(situacao == 2) {
			this.situacaoPrefeitura = new SituacaoCaos();
		}
	}

}
