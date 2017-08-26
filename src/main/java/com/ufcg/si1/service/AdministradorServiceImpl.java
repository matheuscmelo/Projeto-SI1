package com.ufcg.si1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Administrador;
import com.ufcg.si1.repository.AdministradorRepository;
import com.ufcg.si1.repository.USRepository;

@Service("administradorService")
public class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	private AdministradorRepository adms;

	@Override
	public Administrador adicionarAdm(Administrador adm) {
		return adms.save(adm);

	}

	@Override
	public void removerAdm(Administrador adm) {
		adms.delete(adm);

	}

	@Override
	public boolean checarEmail(String email) {
		for (Administrador adm : adms.findAll()) {
			if (adm.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean validarAdm(Administrador adm) {
		boolean resultado = false;
		for (Administrador admin : adms.findAll()) {
			if (admin.getSenha().equals(adm.getSenha())) {
				resultado = true;
			}
		}
		return resultado && this.checarEmail(adm.getEmail());
	}


	public Administrador procurarPorId(long id) {
		return adms.getOne(id);
	}
}
