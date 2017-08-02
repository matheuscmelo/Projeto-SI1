package com.ufcg.si1.model;

public class SituacaoExtra implements IFSituacaoPrefeitura {
	// se extra, mais de 10% abertas eh ruim, mais de 5% eh regular
	//situacao retornada
    //0: RUIM
    //1: REGULAR
    //2: BOM
	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		int situacao = -1;
		
		if ((double) queixasAbertas / queixasTotais > 0.1) {
			situacao = 0;
		} else if ((double) queixasAbertas / queixasTotais > 0.05) {
			situacao = 1;
		} else {
			situacao = 2;
		}
		
		return situacao;
	}

}
