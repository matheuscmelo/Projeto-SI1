package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

public class QueixaFechada implements SituacaoQueixa {

	@Override
	public void emAndamento(Queixa queixa) throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("Status Inválido");

	}

	@Override
	public void fechar(Queixa queixa, String comentario) throws ObjetoInvalidoException {
		throw new ObjetoInvalidoException("Status Inválido");

	}
	
	@Override
	public boolean isAberta() {
		return false;
	}

}
