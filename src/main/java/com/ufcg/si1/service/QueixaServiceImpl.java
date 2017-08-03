package com.ufcg.si1.service;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.SituacaoQueixa;

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

	public void updateQueixa(Queixa queixa) {
		if (queixas.contains(queixa)) {
			int index = queixas.indexOf(queixa);
			queixas.set(index, queixa);
		}
	}
	
	public void fecharqueixa(Queixa queixa) {
		queixa.setSituacao(SituacaoQueixa.FECHADA);
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

}
