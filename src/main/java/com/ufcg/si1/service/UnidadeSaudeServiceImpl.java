package com.ufcg.si1.service;

import com.ufcg.si1.model.Especialidade;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.repository.USRepository;

import exceptions.ObjetoJaExistenteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	
	/*
	 * Metodo para pegar todas unidades de saude que possuem a mesma especialidade(descricao)
	 */
	@Override
	public List<UnidadeSaude> findUSByEspecialidade(String descricao) {
		List<UnidadeSaude> unidades =  this.unidades.findAll();
		List<UnidadeSaude> unidadesPorEspecialidade = new ArrayList<>();
		for (UnidadeSaude us : unidades) {
			for (String esp : us.getEspecialidades()) {
				if (esp.equals(descricao)) {
					unidadesPorEspecialidade.add(us);
				}
			}
		}
		
		return unidadesPorEspecialidade;
	}
	
	@Override
	public UnidadeSaude findByBairro(String bairro) {
		for (UnidadeSaude us : unidades.findAll()) {
			if(us.getBairro().equalsIgnoreCase(bairro.trim())) {
				return us;
			}
		}
		return null;
	}

	@Override
	public List<UnidadeSaude> getUnidadesPorEspecialidade(String especialidade) {
		List<UnidadeSaude> usList = new ArrayList<>();
		for (UnidadeSaude us : unidades.findAll()) {
			if (us.hasEspecialidade(especialidade)) {
				usList.add(us);
			}
		}
		return usList;
		
	}


}