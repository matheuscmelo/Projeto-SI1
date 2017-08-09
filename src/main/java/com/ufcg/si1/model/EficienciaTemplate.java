package com.ufcg.si1.model;

public abstract class EficienciaTemplate {
	
	private static final int RUIM = 0;
	private static final int REGULAR = 1;
	private static final int BOM = 2;
	
	public int calculaEficiencia(int queixasAbertas, int queixasTotais) {
		int eficiencia;
		
		if (condicaoRuim(queixasAbertas, queixasTotais)) {
			eficiencia = RUIM;
		} else if (condicaoRegular(queixasAbertas, queixasTotais)) {
			eficiencia = REGULAR;
		} else {
			eficiencia = BOM;
		}
		
		return eficiencia;
	}
	
	public abstract boolean condicaoRuim(int queixasAbertas, int queixasTotais);
	
	public abstract boolean condicaoRegular(int queixasAbertas, int queixasTotais);
	
}
