package com.ufcg.si1.service;


import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.Queixa;

import exceptions.ObjetoInvalidoException;

public interface QueixaService {

	List<Queixa> findAllQueixas();


    void saveQueixa(Queixa queixa);


	Queixa findById(long id);

	void updateQueixa(Queixa user) throws ObjetoInvalidoException;


	void deleteQueixaById(long id);

    int size();

	Iterator<Queixa> getIterator();


//	boolean isUserExist(Queixa user);
	
}
