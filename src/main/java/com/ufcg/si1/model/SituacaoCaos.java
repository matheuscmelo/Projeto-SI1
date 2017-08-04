package com.ufcg.si1.model;

public class SituacaoCaos implements IFSituacaoPrefeitura {

	private EficienciaTemplate template;

	public SituacaoCaos() {
		this.template = new EficienciaImplCaos();
	}

	// mais de 5% de queixas abertas é ruim e mais de 2% é regular
	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		return template.calculaSituacao(queixasAbertas, queixasTotais);
	}

}
