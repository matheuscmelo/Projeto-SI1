package com.ufcg.si1.model;

public class SituacaoCaos implements IFSituacaoPrefeitura {
	//mais de 5% de queixas abertas é ruim e mais de 2% é regular
	@Override
	public int calculaSituacao(int queixasAbertas, int queixasTotais) {
		int situacao = -1;
		
		if ((double) queixasAbertas / queixasTotais > 0.5) {
			situacao = 0;
		} else if ((double) queixasAbertas / queixasTotais > 0.2) {
			situacao = 1;
		} else {
			situacao = 2;
		}
		
		return situacao;
	}

}
