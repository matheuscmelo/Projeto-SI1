package com.ufcg.si1.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonValue;

@Entity
public class QueixaAberta extends SituacaoQueixa {
	
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
	
	@Override
	@JsonValue
	public String toString() {
		return "ABERTA";
	}
}
