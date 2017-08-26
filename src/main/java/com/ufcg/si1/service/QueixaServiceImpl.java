package com.ufcg.si1.service;

import com.ufcg.si1.model.Endereco;
import com.ufcg.si1.model.Pessoa;
import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.QueixaAberta;
import com.ufcg.si1.repository.EnderecoRepository;
import com.ufcg.si1.repository.PessoaRepository;
import com.ufcg.si1.repository.QueixaRepository;
import com.ufcg.si1.repository.SituacaoQueixaRepository;

import exceptions.ObjetoInvalidoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {

	@Autowired
	private QueixaRepository queixas;
	
	@Autowired
	private SituacaoQueixaRepository situacaoQueixaRepository;
	
	@Autowired
	private PessoaRepository pessoas;
	
	@Autowired
	private EnderecoRepository enderecos;
	
	public List<Queixa> findAllQueixas() {
		return queixas.findAll();
	}

	public void saveQueixa(Queixa queixa) {
		Pessoa pessoa = queixa.getSolicitante();
		Endereco endereco = queixa.getEndereco();
		
		enderecos.save(endereco);
		pessoas.save(pessoa);
		situacaoQueixaRepository.save(queixa.getSituacaoQueixa());
		queixas.save(queixa);
	}

	public void updateQueixa(Queixa queixa) {
		queixas.saveAndFlush(queixa);
	}

	public void fecharQueixa(Long id, String comment) throws ObjetoInvalidoException {
		Queixa queixa = queixas.findOne(id);
		if(queixa != null) {
			queixa.fechar(comment);
			queixas.save(queixa);
		}
	}

	public void abrirQueixa(Queixa queixa) {
		queixa.setSituacao(new QueixaAberta());

	}

	public void deleteQueixaById(long id) {
		Queixa queixa = findById(id);
		if (queixa != null) {
			queixas.delete(queixa);
		}
	}

	@Override
	public int size() {
		return queixas.findAll().size();
	}

	public Queixa findById(long id) {
		return queixas.getOne(id);
	}

	@Override
	public int numeroQueixasAbertas() {
		List<Queixa> allQueixas = queixas.findAll();
		int queixasAbertas = 0;
		for (Queixa queixa : allQueixas) {
			if (queixa.isAberta())
				queixasAbertas++;
		}

		return queixasAbertas;
	}

}
