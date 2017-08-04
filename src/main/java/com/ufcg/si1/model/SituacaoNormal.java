package com.ufcg.si1.model;

public class SituacaoNormal implements IFSituacaoPrefeitura {
	
	private EficienciaTemplate template;
	
	public SituacaoNormal() {
		this.template = new EficienciaImplNormal();
	}
	
	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		return template.calculaSituacao(queixasAbertas, queixasTotais);
	}

}
