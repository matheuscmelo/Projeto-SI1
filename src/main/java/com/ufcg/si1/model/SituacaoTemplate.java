package com.ufcg.si1.model;

public abstract class SituacaoTemplate {
	
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		int situacao = -1;
		
		if (condicaoRuim(queixasAbertas, queixasTotais)) {
			situacao = 0;
		} else if (condicaoRegular(queixasAbertas, queixasTotais)) {
			situacao = 1;
		} else {
			situacao = 2;
		}
		
		return situacao;
	}
	
	public abstract boolean condicaoRuim(int queixasAbertas, int queixasTotais);
	
	public abstract boolean condicaoRegular(int queixasAbertas, int queixasTotais);
	
}
