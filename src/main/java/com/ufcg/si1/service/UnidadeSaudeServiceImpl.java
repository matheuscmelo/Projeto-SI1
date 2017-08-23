package com.ufcg.si1.service;

import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.repository.USRepository;

import exceptions.ObjetoJaExistenteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {
	
	@Autowired
	private USRepository unidades;
	
	@Override
	public UnidadeSaude procura(long codigo) {
		return unidades.getOne(codigo);
	}

	@Override
	public List<UnidadeSaude> getAll() {
		return unidades.findAll();
	}

	@Override
	public void insere(UnidadeSaude us) throws ObjetoJaExistenteException {
		unidades.save(us);
	}

	@Override
	public boolean existe(long codigo) {
		UnidadeSaude us = unidades.getOne(codigo);
		return us != null;
	}

	@Override
	public UnidadeSaude findByBairro(String bairro) {
		return null;
	}


}