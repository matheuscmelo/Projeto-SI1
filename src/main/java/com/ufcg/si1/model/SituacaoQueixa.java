package com.ufcg.si1.model;

import exceptions.ObjetoInvalidoException;

public interface SituacaoQueixa {

	void emAndamento(Queixa queixa) throws ObjetoInvalidoException;
	void fechar(Queixa queixa, String comentario) throws ObjetoInvalidoException;


}
