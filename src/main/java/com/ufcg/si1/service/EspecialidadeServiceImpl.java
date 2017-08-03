package com.ufcg.si1.service;

import com.ufcg.si1.model.Especialidade;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("especialidadeService")
public class EspecialidadeServiceImpl implements EspecialidadeService {

    private Set<Especialidade> especialidades;
    
    public EspecialidadeServiceImpl() {
        especialidades = new HashSet<Especialidade>();
    }

    @Override
    public Especialidade procura(long codigo) throws Rep,
    ObjetoInexistenteException {

        for (Especialidade esp : especialidades) {
        	if (esp.getCodigo() == codigo) 
        		return esp;
        }
        return null;
    }

    @Override
    public List getEspecialidades() {
        return Arrays.asList(especialidades);
    }

    @Override
    public int size() {
        return especialidades.size();
    }

    @Override
    public void insere(Especialidade esp) throws Rep,
    ObjetoJaExistenteException {
        this.especialidades.add(esp);
    }

    @Override
    public boolean existe(long codigo) {
    	boolean existeEsp = false;
    	for (Especialidade esp : especialidades) {
        	if (esp.getCodigo() == codigo) 
        		existeEsp = true;
        }
    	
    	return existeEsp;
    }
}
