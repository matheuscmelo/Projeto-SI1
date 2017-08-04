package com.ufcg.si1.model;

public class SituacaoExtra implements IFSituacaoPrefeitura {
	
	private EficienciaTemplate template;

	public SituacaoExtra() {
		this.template = new EficienciaImplExtra();
	}
	
	// se extra, mais de 10% abertas eh ruim, mais de 5% eh regular
	//situacao retornada
    //0: RUIM
    //1: REGULAR
    //2: BOM
	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		return template.calculaSituacao(queixasAbertas, queixasTotais);
	}

}
