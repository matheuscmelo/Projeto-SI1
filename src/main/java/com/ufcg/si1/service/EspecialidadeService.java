package com.ufcg.si1.service;

import com.ufcg.si1.model.Especialidade;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;

import java.util.List;


public interface EspecialidadeService {
    Especialidade procura(long codigo) throws Rep,
    ObjetoInexistenteException;

    List getEspecialidades();

    int size();
    
    void insere(Especialidade esp)throws Rep,
            ObjetoJaExistenteException;

    boolean existe(long codigo);

}
