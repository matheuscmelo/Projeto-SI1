package com.ufcg.si1.model;

public class SituacaoNormal extends SituacaoTemplate {

	@Override
	public boolean condicaoRuim(int queixasAbertas, int queixasTotais) {
		return (double) queixasAbertas / queixasTotais > 0.2;
	}
	
	@Override
	public boolean condicaoRegular(int queixasAbertas, int queixasTotais) {
		return (double) queixasAbertas / queixasTotais > 0.1;
	}

}
