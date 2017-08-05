package com.ufcg.si1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.service.UnidadeSaudeService;
import com.ufcg.si1.service.UnidadeSaudeServiceImpl;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;

import exceptions.ObjetoJaExistenteException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UnidadeSaudeREST {


	private UnidadeSaudeService unidadeSaudeService = new UnidadeSaudeServiceImpl();


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
}
