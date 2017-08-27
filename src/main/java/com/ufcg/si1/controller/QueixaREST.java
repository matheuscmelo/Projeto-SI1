package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.QueixaAlimentar;
import com.ufcg.si1.model.QueixaAnimal;
import com.ufcg.si1.model.QueixaServico;
import com.ufcg.si1.model.Prefeitura;
import com.ufcg.si1.service.QueixaService;
import com.ufcg.si1.service.QueixaServiceImpl;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QueixaREST {
	
	@Autowired
	private QueixaService queixaService;


	@RequestMapping(value = "/queixa/", method = RequestMethod.GET)
	public ResponseEntity<List<Queixa>> listAllQueixas() {
		List<Queixa> queixas = queixaService.findAllQueixas();
		return new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/alimentar", method = RequestMethod.POST)
	public ResponseEntity<Queixa> abrirQueixaAlimentar(@RequestBody QueixaAlimentar queixa) {
		queixaService.saveQueixa(queixa);
		return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/queixa/animal", method = RequestMethod.POST)
	public ResponseEntity<Queixa> abrirQueixaAnimal(@RequestBody QueixaAnimal queixa) {
		queixaService.saveQueixa(queixa);
		return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/queixa/geral", method = RequestMethod.POST)
	public ResponseEntity<Queixa> abrirQueixaGeral(@RequestBody QueixaServico queixa) {
		queixaService.saveQueixa(queixa);
		return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/queixa/fechamento/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> fecharQueixa(@PathVariable("id") long id, @RequestBody Queixa queixaAFechar) throws ObjetoInvalidoException {
		queixaService.fecharQueixa(id, queixaAFechar.getComentario());;
		return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Queixa> consultarQueixa(@PathVariable("id") long id) {	
		Queixa queixa = queixaService.findById(id);
		if (queixa == null) {
			return new ResponseEntity<Queixa>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Queixa>(queixa, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQueixa(@PathVariable("id") long id, @RequestBody Queixa queixa)
			throws ObjetoInvalidoException {

		Queixa currentQueixa = queixaService.findById(id);

		if (currentQueixa == null) {
			return new ResponseEntity<Queixa>(HttpStatus.NOT_FOUND);
		}

		currentQueixa.setDescricao(queixa.getDescricao());
		currentQueixa.setComentario(queixa.getComentario());

		queixaService.updateQueixa(currentQueixa);
		return new ResponseEntity<Queixa>(currentQueixa, HttpStatus.OK);
	}

	@RequestMapping(value = "/queixa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQueixa(@PathVariable("id") long id) {

		Queixa user = queixaService.findById(id);
		if (user == null) {
			return new ResponseEntity<Queixa>(HttpStatus.NOT_FOUND);
		}
		queixaService.deleteQueixaById(id);
		return new ResponseEntity<Queixa>(HttpStatus.NO_CONTENT);
	}

	

	
	
}
