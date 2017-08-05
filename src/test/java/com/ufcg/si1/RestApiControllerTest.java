package com.ufcg.si1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.controller.EspecialidadeREST;
import com.ufcg.si1.controller.QueixaREST;
import com.ufcg.si1.controller.UnidadeSaudeREST;
import com.ufcg.si1.model.Especialidade;
import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.SituacaoQueixa;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.service.UnidadeSaudeService;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoInvalidoException;
import exceptions.Rep;

public class RestApiControllerTest {

	QueixaREST queixaRest;
	EspecialidadeREST espRest;
	UnidadeSaudeREST unidadeRest;

	@Before
	public void setup() {

		this.queixaRest = new QueixaREST();
		this.espRest = new EspecialidadeREST();
		this.unidadeRest = new UnidadeSaudeREST();
	}

	@Test
	public void testAdicaoQueixa() throws ObjetoInvalidoException {
		Queixa q = new Queixa(0, "ddd", "fff", "wesley", "fff", "fff", "fff", "fff");
		assertTrue(this.queixaRest.numeroQueixasAbertas() == 0);
		this.queixaRest.abrirQueixa(q);
		assertTrue(this.queixaRest.numeroQueixasAbertas() == 1);
		assertEquals(this.queixaRest.consultarQueixa(1), new ResponseEntity<Queixa>(q, HttpStatus.OK));
		this.queixaRest.fecharQueixa(q);
		assertEquals(this.queixaRest.numeroQueixasAbertas(), 0);
	}

	@Test
	public void testAdicaoEspecialidade() throws Rep, ObjetoInexistenteException {
		Especialidade e = new Especialidade("dá muito é o butão", 1);
		this.espRest.incluirEspecialidade(e);
		assertEquals(this.espRest.consultarEspecialidade(1), new ResponseEntity<Especialidade>(e, HttpStatus.OK));
		Especialidade e1 = new Especialidade("fazer menes", 2);
		this.espRest.incluirEspecialidade(e1);
		assertEquals(this.espRest.consultarEspecialidade(2), new ResponseEntity<Especialidade>(e1, HttpStatus.OK));
		Especialidade e2 = new Especialidade("fazer menes");
		this.espRest.incluirEspecialidade(e2);
	}

	@Test
	public void testUS() {
		UnidadeSaude us = new UnidadeSaude("lugar de dá a bunda", 24, 4, "Hospital", "zepa");
		us.setCodigo(0);
		this.unidadeRest.incluirUnidadeSaude(us);
		assertEquals(this.unidadeRest.consultarUnidadeSaude(0), new ResponseEntity<>(us, HttpStatus.OK));
		assertEquals(this.unidadeRest.consultarUnidadeSaude(2),
				new ResponseEntity(new CustomErrorType("Unidade with id " + 2 + " not found"), HttpStatus.NOT_FOUND));
	}
}
