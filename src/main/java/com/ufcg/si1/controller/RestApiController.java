package com.ufcg.si1.controller;

import br.edu.ufcg.Hospital;
import com.ufcg.si1.model.*;
import com.ufcg.si1.service.*;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestApiController {

	private QueixaService queixaService = new QueixaServiceImpl();
	private EspecialidadeService especialidadeService = new EspecialidadeServiceImpl();
	private UnidadeSaudeService unidadeSaudeService = new UnidadeSaudeServiceImpl();

	/*
	 * situação normal =0 situação extra =1
	 */
	private SituacaoPrefeitura situacaoPrefeitura = new SituacaoPrefeitura();

	// -------------------Retrieve All
	// Complaints---------------------------------------------

	@RequestMapping(value = "/queixa/", method = RequestMethod.GET)
	public ResponseEntity<List<Queixa>> listAllUsers() {
		List<Queixa> queixas = queixaService.findAllQueixas();

		if (queixas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK);
	}

	// -------------------Abrir uma
	// Queixa-------------------------------------------

	@RequestMapping(value = "/queixa/", method = RequestMethod.POST)
	public ResponseEntity<Queixa> abrirQueixa(@RequestBody Queixa queixa) {
		queixaService.saveQueixa(queixa);
		return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Queixa> consultarQueixa(@PathVariable("id") long id) {

		Queixa q = queixaService.findById(id);
		if (q == null) {
			return new ResponseEntity(new CustomErrorType("Queixa with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Queixa>(q, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQueixa(@PathVariable("id") long id, @RequestBody Queixa queixa) throws ObjetoInvalidoException {

		Queixa currentQueixa = queixaService.findById(id);

		if (currentQueixa == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Queixa with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentQueixa.setDescricao(queixa.getDescricao());
		currentQueixa.setComentario(queixa.getComentario());

		queixaService.updateQueixa(currentQueixa);
		return new ResponseEntity<Queixa>(currentQueixa, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Queixa user = queixaService.findById(id);
		if (user == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Queixa with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		queixaService.deleteQueixaById(id);
		return new ResponseEntity<Queixa>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/queixa/fechamento", method = RequestMethod.POST)
	public ResponseEntity<?> fecharQueixa(@RequestBody Queixa queixaAFechar) throws ObjetoInvalidoException {
		queixaService.updateQueixa(queixaAFechar);
		return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
	}

	// Especialidade

	@RequestMapping(value = "/especialidade/unidades", method = RequestMethod.GET)
	public ResponseEntity<?> consultaEspecialidadeporUnidadeSaude(@RequestBody int codigoUnidadeSaude) {

		UnidadeSaude us = unidadeSaudeService.procura(codigoUnidadeSaude);

		if (us == null) {
			return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(us.getEspecialidades(), HttpStatus.OK);

	}

	@RequestMapping(value = "/unidade/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUnidades() {
		Set<UnidadeSaude> unidades = unidadeSaudeService.getAll();
		if (unidades.isEmpty())
			return new ResponseEntity<List>(HttpStatus.NOT_FOUND);
		else {
			List<UnidadeSaude> unidadeSaudes = new ArrayList<>();
			for (Object saude : unidades) {
				if (saude instanceof UnidadeSaude) {
					unidadeSaudes.add((UnidadeSaude) saude);
				}
			}
			return new ResponseEntity<>(unidadeSaudes, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/especialidade/", method = RequestMethod.POST)
	public ResponseEntity<String> incluirEspecialidade(
			@RequestBody Especialidade esp/* , UriComponentsBuilder ucBuilder */) {
		try {
			especialidadeService.insere(esp);
		} catch (Rep e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (ObjetoJaExistenteException e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/especialidade/{id}").buildAndExpand(esp.getCodigo()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// how to save a subclass object?
	@RequestMapping(value = "/unidade/", method = RequestMethod.POST)
	public ResponseEntity<String> incluirUnidadeSaude(
			@RequestBody UnidadeSaude us/* , UriComponentsBuilder ucBuilder */) {

		try {
			unidadeSaudeService.insere(us);
		} catch (ObjetoJaExistenteException e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/unidade/{id}").buildAndExpand(us.pegaCodigo()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/especialidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarEspecialidade(@PathVariable("id") long id)
			throws Rep, ObjetoInexistenteException {

		Especialidade q = especialidadeService.procura(id);
		if (q == null) {
			return new ResponseEntity(new CustomErrorType("Especialidade with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Especialidade>(q, HttpStatus.OK);
	}

	@RequestMapping(value = "/unidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarUnidadeSaude(@PathVariable("id") long id) {

		Object us = unidadeSaudeService.procura(id);
		if (us == null) {
			return new ResponseEntity(new CustomErrorType("Unidade with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(us, HttpStatus.OK);
	}

	@RequestMapping(value = "/geral/medicos/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> calcularMediaMedicoPacienteDia(@PathVariable("id") long id) {

		UnidadeSaude unidade = unidadeSaudeService.procura(id);

		if (unidade == null) {
			return new ResponseEntity<ObjWrapper<Double>>(HttpStatus.NOT_FOUND);
		}

		double result = unidade.getNumeroMedicos() / unidade.getNumeroPacientesDia();

		return new ResponseEntity<ObjWrapper<Double>>(new ObjWrapper<Double>(new Double(result)), HttpStatus.OK);
	}

	@RequestMapping(value = "/geral/situacao", method = RequestMethod.GET)
	public ResponseEntity<?> getSituacaoGeralQueixas() {

		int situacao = situacaoPrefeitura.calculaSituacao(numeroQueixasAbertas(), queixaService.size());

		return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(situacao), HttpStatus.OK);
	}

	@RequestMapping(value = "/unidade/busca", method = RequestMethod.GET)
	public ResponseEntity<?> consultarUnidadeSaudePorBairro(
			@RequestParam(value = "bairro", required = true) String bairro) {

		UnidadeSaude us = unidadeSaudeService.findByBairro(bairro);
		if (us == null) {
			return new ResponseEntity(new CustomErrorType("Unidade with bairro " + bairro + " not found"),
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UnidadeSaude>(us, HttpStatus.OK);
	}

	public int numeroQueixasAbertas() {
		int contador = 0;
		Iterator<Queixa> it = queixaService.getIterator();
		for (Iterator<Queixa> it1 = it; it1.hasNext();) {
			Queixa q = it1.next();
			if (q.getSituacao() == SituacaoQueixa.ABERTA)
				contador++;
		}

		return contador;
	}

}
