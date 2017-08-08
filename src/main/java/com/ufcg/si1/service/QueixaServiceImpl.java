package com.ufcg.si1.service;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.SituacaoQueixa;

import exceptions.ObjetoInvalidoException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Queixa> queixas = new ArrayList<Queixa>();

	public List<Queixa> findAllQueixas() {
		return queixas;
	}

	public void saveQueixa(Queixa queixa) {
		queixa.setId(counter.incrementAndGet());
		queixas.add(queixa);
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
		queixa.setSituacao(SituacaoQueixa.ABERTA);
	}

	public void deleteQueixaById(long id) {

		for (Iterator<Queixa> iterator = queixas.iterator(); iterator.hasNext();) {
			Queixa q = iterator.next();
			if (q.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public int size() {
		return queixas.size();
	}

	@Override
	public Iterator<Queixa> getIterator() {
		return queixas.iterator();
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
		int contador = 0;
		Iterator<Queixa> it = this.getIterator();
		for (Iterator<Queixa> it1 = it; it1.hasNext();) {
			Queixa q = it1.next();
			if (q.getSituacao() == SituacaoQueixa.ABERTA)
				contador++;
		}

		return contador;
	}

}
