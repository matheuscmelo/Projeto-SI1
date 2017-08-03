package com.ufcg.si1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;


public class UnidadeSaude {
	@JsonProperty(value = "codigo")
	private long codigo; // gerado no repositorio
	@JsonProperty(value = "descricao")
	private String descricao;
	@JsonProperty(value = "especialidades")
	private Set<Especialidade> especialidades;
	@JsonProperty(value = "type")
	private String tipo;
	@JsonProperty(value = "numeroPacientesDia")
	private int numeroPacientesDia;
	@JsonProperty(value = "numeroMedicos")
	private int numeroMedicos;
	@JsonProperty(value = "bairro")
	private String bairro;

	public UnidadeSaude(String descricao, int numeroPacientesDia, int numeroMedicos, String tipo, String bairro,
			Set<Especialidade> especialidades) {
		this.especialidades = new HashSet<>();
		this.descricao = descricao;
		this.numeroMedicos = numeroMedicos;
		this.numeroPacientesDia = numeroPacientesDia;
		this.tipo = tipo;
		this.bairro = bairro;
		this.especialidades = especialidades;
	}

	public UnidadeSaude(String descricao, int numeroPacientesDia, int numeroMedicos, String tipo, String bairro) {
		this(descricao, numeroPacientesDia, numeroMedicos, tipo, bairro, new HashSet<>());
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNumeroPacientesDia() {
		return numeroPacientesDia;
	}

	public void setNumeroPacientesDia(int numeroPacientesDia) {
		this.numeroPacientesDia = numeroPacientesDia;
	}

	public int getNumeroMedicos() {
		return numeroMedicos;
	}

	public void setNumeroMedicos(int numeroMedicos) {
		this.numeroMedicos = numeroMedicos;
	}

	public boolean adicionarEspecialidade(Especialidade especialidade) {
		return this.especialidades.add(especialidade);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Object getEspecialidades() {
		return especialidades;
	}

}
