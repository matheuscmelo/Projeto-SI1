package com.ufcg.si1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.controller.RestApiController;
import com.ufcg.si1.model.Especialidade;
import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInexistenteException;
import exceptions.Rep;

public class RestApiControllerTest {
		RestApiController controller; 
		
		
		@Before
		public void setup() {
			this.controller = new RestApiController();
		}
		
		@Test
		public void testAdicaoQueixa() {
			Queixa q = new Queixa(0, "ddd", 0, "fff", "wesley", "fff", "fff", "fff", "fff");
			assertTrue(this.controller.numeroQueixasAbertas() == 0);
			this.controller.abrirQueixa(q);
			assertTrue(this.controller.numeroQueixasAbertas() == 1);
			assertEquals(this.controller.consultarQueixa(1), new ResponseEntity<Queixa>(q, HttpStatus.OK));
			this.controller.fecharQueixa(q);
			assertEquals(this.controller.numeroQueixasAbertas(), 0);
		}
		 @Test
		public void testAdicaoEspecialidade() throws Rep, ObjetoInexistenteException {
			Especialidade e = new Especialidade("dá muito é o butão", 1);
			this.controller.incluirEspecialidade(e);
			assertEquals(this.controller.consultarEspecialidade(1),new ResponseEntity<Especialidade>(e, HttpStatus.OK));
			Especialidade e1 = new Especialidade("fazer menes", 2);
			this.controller.incluirEspecialidade(e1);
			assertEquals(this.controller.consultarEspecialidade(2),new ResponseEntity<Especialidade>(e1, HttpStatus.OK));
			Especialidade e2 = new Especialidade("fazer menes");
			this.controller.incluirEspecialidade(e2);
		}
		 
		@Test
		public void testUS() {
			UnidadeSaude us = new UnidadeSaude("lugar de dá a bunda", 24, 4, "Hospital", "zepa");
			us.setCodigo(0);
			this.controller.incluirUnidadeSaude(us);
			assertEquals(this.controller.consultarUnidadeSaude(0), new ResponseEntity<>(us, HttpStatus.OK));
			assertEquals(this.controller.consultarUnidadeSaude(2), new ResponseEntity(new CustomErrorType("Unidade with id " + 2 + " not found"), HttpStatus.NOT_FOUND));
		}
}
