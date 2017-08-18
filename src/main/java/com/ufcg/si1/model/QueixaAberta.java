package com.ufcg.si1.model;

public class QueixaAberta implements SituacaoQueixa {

	@Override
	public void emAndamento(Queixa queixa) {
		queixa.setSituacao(new QueixaEmAndamento());

	}

	@Override
	public void fechar(Queixa queixa, String comentario) {
		queixa.setSituacao(new QueixaFechada());
		queixa.setComentario(comentario);
	}
	
	@Override
	public boolean isAberta() {
		return true;
	}
}
