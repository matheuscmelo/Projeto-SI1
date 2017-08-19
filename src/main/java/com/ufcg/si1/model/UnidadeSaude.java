package com.ufcg.si1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="UnidadeSaude")
@Table(name="tb_US")
public class UnidadeSaude {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	@Column
	@JsonProperty(value = "descricao")
	private String descricao;
	
//	@JsonProperty(value = "especialidades")
//	private Set<Especialidade> especialidades;
	@Column
	@JsonProperty(value = "type")
	private String tipo;
	@Column
	@JsonProperty(value = "numeroPacientesDia")
	private int numeroPacientesDia;
	@Column
	@JsonProperty(value = "numeroMedicos")
	private int numeroMedicos;
	@Column
	@JsonProperty(value = "bairro")
	private String bairro;

	public UnidadeSaude() {}
	public UnidadeSaude(String descricao, int numeroPacientesDia, int numeroMedicos, String tipo, String bairro) {
//		this.especialidades = new HashSet<>();
		this.descricao = descricao;
		this.numeroMedicos = numeroMedicos;
		this.numeroPacientesDia = numeroPacientesDia;
		this.tipo = tipo;
		this.bairro = bairro;
	}

	public long getCodigo() {
		return ID;
	}

	public void setCodigo(long codigo) {
		this.ID = codigo;
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

//	public boolean adicionarEspecialidade(Especialidade especialidade) {
//		return this.especialidades.add(especialidade);
//	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

//	public Object getEspecialidades() {
//		return especialidades;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
//		result = prime * result + ((especialidades == null) ? 0 : especialidades.hashCode());
		result = prime * result + numeroMedicos;
		result = prime * result + numeroPacientesDia;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeSaude other = (UnidadeSaude) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (ID != other.ID)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
//		if (especialidades == null) {
//			if (other.especialidades != null)
//				return false;
//		
//		} else if (!especialidades.equals(other.especialidades))
//			return false;
		if (numeroMedicos != other.numeroMedicos)
			return false;
		if (numeroPacientesDia != other.numeroPacientesDia)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
