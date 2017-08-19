package com.ufcg.si1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import exceptions.ObjetoInvalidoException;


@Entity(name="QueixaType")
@Table(name="tb_queixa_types")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SituacaoQueixa {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	public abstract void emAndamento(Queixa queixa) throws ObjetoInvalidoException;
	public abstract void fechar(Queixa queixa, String comentario) throws ObjetoInvalidoException;
	public abstract boolean isAberta();

}
