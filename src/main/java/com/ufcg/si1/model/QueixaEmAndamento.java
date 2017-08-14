package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

public class QueixaEmAndamento implements SituacaoQueixa {

	@Override
	public void emAndamento(Queixa queixa) throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("Status Inválido");

	}

	@Override
	public void fechar(Queixa queixa, String comentario) {
		queixa.setSituacao(new QueixaFechada());
		queixa.setComentario(comentario);
	}

}
