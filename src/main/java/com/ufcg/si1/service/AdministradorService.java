package com.ufcg.si1.service;

import com.ufcg.si1.model.Administrador;

public interface AdministradorService {

	Administrador adicionarAdm(Administrador adm);

	void removerAdm(Administrador adm);

	boolean checarEmail(String email);

	boolean validarAdm(Administrador adm);

	Administrador procurarPorId(long id);
}
