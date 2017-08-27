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
	public Administrador findByEmail(String email) {
		for (Administrador adm : adms.findAll()) {
			if (adm.getEmail().equals(email)) {
				return adm;
			}
		}
		return null;
	}

	@Override
	public boolean validarAdm(Administrador adm) {
		Administrador adm2 = findByEmail(adm.getEmail());
		if (adm2 != null) {
			return adm2.getSenha().equals(adm.getSenha());
		}
		return false;
	}

	public Administrador findById(long id) {
		return adms.getOne(id);
	}

	@Override
	public boolean contains(Administrador adm) {
		return findByEmail(adm.getEmail()) != null;
	}
}
