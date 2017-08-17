package com.ufcg.si1.service;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.QueixaAberta;
import com.ufcg.si1.model.SituacaoQueixa;

import exceptions.ObjetoInvalidoException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {

	private List<Queixa> queixas = new ArrayList<Queixa>();

	public List<Queixa> findAllQueixas() {
		return queixas;
	}

	public void saveQueixa(Queixa queixa) {
		queixas.add(queixa);
		queixa.setId(this.size());
	}

	public void updateQueixa(Queixa queixa) throws ObjetoInvalidoException {
		if (queixas.contains(queixa)) {
			this.fecharqueixa(queixa, queixa.getComentario());
			int index = queixas.indexOf(queixa);
			queixas.set(index, queixa);
		}
	}

	public void fecharqueixa(Queixa queixa, String coment) throws ObjetoInvalidoException {
		queixa.fechar(coment);
	}

	public void abrirQueixa(Queixa queixa) {
		queixa.setSituacao(new QueixaAberta());
		
	}

	public void deleteQueixaById(long id) {
		Queixa queixa = findById(id);
		queixas.remove(queixa);
	}

	@Override
	public int size() {
		return queixas.size();
	}

	public void deleteAllUsers() {
		queixas.clear();
	}

	public Queixa findById(long id) {
		for (Queixa queixa : queixas) {
			if (queixa.getId() == id) {
				return queixa;
			}
		}
		return null;
	}

	@Override
	public int numeroQueixasAbertas() {
		int queixasAbertas = 0;
		for (Queixa queixa : queixas) {
			if (queixa.getSituacao() instanceof QueixaAberta)
				queixasAbertas++;
		}

		return queixasAbertas;
	}

}
