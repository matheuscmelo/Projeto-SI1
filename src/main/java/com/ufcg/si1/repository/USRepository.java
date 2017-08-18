package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.UnidadeSaude;

public interface USRepository extends JpaRepository<UnidadeSaude, Long> {
	
}
