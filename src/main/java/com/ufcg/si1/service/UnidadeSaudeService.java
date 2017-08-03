package com.ufcg.si1.service;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import java.util.Set;

import com.ufcg.si1.model.UnidadeSaude;

public interface UnidadeSaudeService {
	UnidadeSaude procura(long codigo);

	Set<UnidadeSaude> getAll();

	void insere(UnidadeSaude us) throws ObjetoJaExistenteException;

	boolean existe(long codigo);

	UnidadeSaude findByBairro(String bairro);
}
