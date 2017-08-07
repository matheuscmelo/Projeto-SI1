package com.ufcg.si1.model;

public abstract class SituacaoTemplate {
	
	private static final int RUIM = 0;
	private static final int REGULAR = 1;
	private static final int BOM = 2;
	
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		int situacao;
		
		if (condicaoRuim(queixasAbertas, queixasTotais)) {
			situacao = RUIM;
		} else if (condicaoRegular(queixasAbertas, queixasTotais)) {
			situacao = REGULAR;
		} else {
			situacao = BOM;
		}
		
		return situacao;
	}
	
	public abstract boolean condicaoRuim(int queixasAbertas, int queixasTotais);
	
	public abstract boolean condicaoRegular(int queixasAbertas, int queixasTotais);
	
}
