package com.ufcg.si1.service;

import com.ufcg.si1.model.UnidadeSaude;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {
	private Set<UnidadeSaude> unidades;

	public UnidadeSaudeServiceImpl() {
		this.unidades = new HashSet<>();
	}

	@Override
	public UnidadeSaude procura(long codigo) {
		for (UnidadeSaude unidadeSaude : unidades) {
			if (unidadeSaude.getCodigo() == codigo) {
				return unidadeSaude;
			}
		}
		return null;
	}

	@Override
	public Set<UnidadeSaude> getAll() {
		return unidades;
	}

	@Override
	public void insere(UnidadeSaude us) throws ObjetoJaExistenteException {
		if (unidades.contains(us))
			throw new ObjetoJaExistenteException("Unidade de saude ja adicionada.");
		this.unidades.add(us);
	}

	@Override
	public boolean existe(long codigo) {
		for (UnidadeSaude unidadeSaude : unidades) {
			if (unidadeSaude.getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}

	@Override
	public UnidadeSaude findByBairro(String bairro) {
		for (UnidadeSaude unidadeSaude : unidades) {
			if (unidadeSaude.getBairro().equals(bairro)) {
				return unidadeSaude;
			}
		}
		return null;
	}


}