package com.ufcg.si1.model;

public class SituacaoExtra extends EficienciaTemplate {

	@Override
	public boolean condicaoRuim(int queixasAbertas, int queixasTotais) {
		return (double) queixasAbertas / queixasTotais > 0.1;
	}

	@Override
	public boolean condicaoRegular(int queixasAbertas, int queixasTotais) {
		return (double) queixasAbertas / queixasTotais > 0.05;
	}

}
