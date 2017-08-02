package com.ufcg.si1.model;

public class SituacaoNormal implements IFSituacaoPrefeitura {

	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		int situacao = -1;
		
		if ((double) queixasAbertas / queixasTotais > 0.2) {
			situacao = 0;
		} else if ((double) queixasAbertas / queixasTotais > 0.1) {
			situacao = 1;
		} else {
			situacao = 2;
		}
		
		return situacao;
	}

}
