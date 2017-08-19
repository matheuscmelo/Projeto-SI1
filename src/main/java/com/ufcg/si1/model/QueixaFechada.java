package com.ufcg.si1.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonValue;

import exceptions.ObjetoInvalidoException;
@Entity
public class QueixaFechada extends SituacaoQueixa {

	
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
	
	@Override
	@JsonValue
	public String toString() {
		return "FECHADA";
	}

}
