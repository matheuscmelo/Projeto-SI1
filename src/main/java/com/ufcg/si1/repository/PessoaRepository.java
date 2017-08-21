package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
