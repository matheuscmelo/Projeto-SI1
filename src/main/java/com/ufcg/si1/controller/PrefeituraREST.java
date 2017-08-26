package com.ufcg.si1.controller;

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
import com.ufcg.si1.model.Administrador;
import com.ufcg.si1.model.Prefeitura;
import com.ufcg.si1.service.AdministradorService;
import com.ufcg.si1.service.AdministradorServiceImpl;
import com.ufcg.si1.service.QueixaService;
import com.ufcg.si1.service.QueixaServiceImpl;
import com.ufcg.si1.util.ObjWrapper;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PrefeituraREST {

	@Autowired
	private Prefeitura situacaoPrefeitura = new Prefeitura();
	private QueixaService queixaService = new QueixaServiceImpl();
	private AdministradorService admService = new AdministradorServiceImpl();

	@RequestMapping(value = "/geral/situacao", method = RequestMethod.GET)
	public ResponseEntity<?> getSituacaoGeralQueixas() {

		int situacao = situacaoPrefeitura.calculaEficiencia(queixaService.numeroQueixasAbertas(), queixaService.size());

		return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(situacao), HttpStatus.OK);
	}

	@RequestMapping(value = "/prefeitua/situacao/caos", method = RequestMethod.PUT)
	public ResponseEntity<?> mudaParaCaos() {
		situacaoPrefeitura.mudaParaCaos();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/prefeitua/situacao/extra", method = RequestMethod.PUT)
	public ResponseEntity<?> mudaParaExtra() {
		situacaoPrefeitura.mudaParaExtra();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/prefeitua/situacao/caos", method = RequestMethod.PUT)
	public ResponseEntity<?> mudarParaNormal() {
		situacaoPrefeitura.mudaParaNormal();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/prefeitura/", method = RequestMethod.POST)
	public ResponseEntity<Administrador> cadastrarAdm(@RequestBody Administrador adm) {
		if (admService.checarEmail(adm.getEmail())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Administrador admAdicionado = admService.adicionarAdm(adm);
		return new ResponseEntity<Administrador>(admAdicionado, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/prefeitura/login", method = RequestMethod.POST)
	public ResponseEntity<Administrador> logar(@RequestBody Administrador adm) {
		if (admService.validarAdm(adm)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/prefeitura/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Administrador> removerAdm(@PathVariable long id) {
		Administrador adm = admService.procurarPorId(id);
		if(adm == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		admService.removerAdm(adm);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
