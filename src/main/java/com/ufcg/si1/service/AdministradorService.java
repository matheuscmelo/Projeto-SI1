package com.ufcg.si1.service;

import com.ufcg.si1.model.Administrador;

public interface AdministradorService {

	Administrador adicionarAdm(Administrador adm);

	void removerAdm(Administrador adm);

	Administrador findByEmail(String email);

	boolean validarAdm(Administrador adm);

	Administrador findById(long id);
	
	boolean contains(Administrador adm);
}
