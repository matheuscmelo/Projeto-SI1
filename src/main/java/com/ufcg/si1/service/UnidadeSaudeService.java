package com.ufcg.si1.service;

import exceptions.ObjetoJaExistenteException;

import java.util.List;

import com.ufcg.si1.model.UnidadeSaude;

public interface UnidadeSaudeService {
	UnidadeSaude procura(long codigo);

	List<UnidadeSaude> getAll();

	void insere(UnidadeSaude us) throws ObjetoJaExistenteException;

	boolean existe(long codigo);
	
	UnidadeSaude findByBairro(String bairro);
	
	List<UnidadeSaude> findUSByEspecialidade(String descricao);

	List<UnidadeSaude> getUnidadesPorEspecialidade(String especialidade);
}
