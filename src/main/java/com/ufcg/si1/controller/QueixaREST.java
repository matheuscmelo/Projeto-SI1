package com.ufcg.si1.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.QueixaAberta;
import com.ufcg.si1.model.SituacaoPrefeitura;
import com.ufcg.si1.model.SituacaoQueixa;
import com.ufcg.si1.service.QueixaService;
import com.ufcg.si1.service.QueixaServiceImpl;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QueixaREST {

	private QueixaService queixaService = new QueixaServiceImpl();

	// situação normal =0 situação extra =1

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

	@RequestMapping(value = "/queixa/fechamento", method = RequestMethod.POST)
	public ResponseEntity<?> fecharQueixa(@RequestBody Queixa queixaAFechar) throws ObjetoInvalidoException {
		queixaService.updateQueixa(queixaAFechar);
		return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Queixa> consultarQueixa(@PathVariable("id") long id) {	
		Queixa queixa = queixaService.findById(id);
		if (queixa == null) {
			return new ResponseEntity(new CustomErrorType("Queixa with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Queixa>(queixa, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQueixa(@PathVariable("id") long id, @RequestBody Queixa queixa)
			throws ObjetoInvalidoException {

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

	@RequestMapping(value = "/geral/situacao", method = RequestMethod.GET)
	public ResponseEntity<?> getSituacaoGeralQueixas() {

		int situacao = situacaoPrefeitura.calculaEficiencia(queixaService.numeroQueixasAbertas(), queixaService.size());

		return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(situacao), HttpStatus.OK);
	}

	
	
}
