package com.ufcg.si1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import com.ufcg.si1.util.ObjWrapper;

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
		Queixa queixa1 = new Queixa(1, "ddd", "fff", "wesley", "fff", "fff", "fff", "fff");
		
		assertEquals(new ResponseEntity<Queixa>(HttpStatus.NO_CONTENT), this.queixaRest.listAllUsers());
		this.queixaRest.abrirQueixa(queixa1);

		ArrayList<Queixa> queixas = new ArrayList<Queixa>();
		queixas.add(queixa1);
		assertEquals(new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK), this.queixaRest.listAllUsers());

		assertEquals(this.queixaRest.consultarQueixa(1), new ResponseEntity<Queixa>(queixa1, HttpStatus.OK));

		this.queixaRest.fecharQueixa(queixa1);

	}

	@Test
	public void testAdicaoEspecialidade() throws Rep, ObjetoInexistenteException {
		Especialidade e = new Especialidade("dá muito é o butão");
		this.espRest.incluirEspecialidade(e);
		assertEquals(this.espRest.consultarEspecialidade(1), new ResponseEntity<Especialidade>(e, HttpStatus.OK));
		Especialidade e1 = new Especialidade("fazer menes");
		this.espRest.incluirEspecialidade(e1);
		assertEquals(this.espRest.consultarEspecialidade(2), new ResponseEntity<Especialidade>(e1, HttpStatus.OK));
		Especialidade e2 = new Especialidade("fazer menes");
		this.espRest.incluirEspecialidade(e2);

	}

	@Test
	public void testUS() {

		UnidadeSaude us1 = new UnidadeSaude("lugar de dá a bunda", 24, 4, "Hospital", "zepa");
		UnidadeSaude us2 = new UnidadeSaude("canto nenhum", 60, 1, "Posto de Saude", "catole");
		UnidadeSaude us3 = new UnidadeSaude("Posto de saude de olivedos city", 5, 6, "Posto de saude", "catole");
		us1.setCodigo(1);
		us2.setCodigo(2);

		HashSet<UnidadeSaude> unidades = new HashSet<>();
		unidades.add(us1);
		unidades.add(us2);
		unidades.add(us3);
		assertEquals(this.unidadeRest.getAllUnidades(), new ResponseEntity<List>(HttpStatus.NOT_FOUND));
		assertEquals(this.unidadeRest.incluirUnidadeSaude(us1), new ResponseEntity<String>(HttpStatus.CREATED));
		assertEquals(this.unidadeRest.incluirUnidadeSaude(us1), new ResponseEntity<String>(HttpStatus.CONFLICT));
		assertEquals(this.unidadeRest.incluirUnidadeSaude(us2), new ResponseEntity<String>(HttpStatus.CREATED));
		assertEquals(this.unidadeRest.incluirUnidadeSaude(us3), new ResponseEntity<String>(HttpStatus.CREATED));

		assertEquals(this.unidadeRest.consultarUnidadeSaude(1), new ResponseEntity<>(us1, HttpStatus.OK));
		assertEquals(this.unidadeRest.consultarUnidadeSaude(3),

				new ResponseEntity(new CustomErrorType("Unidade with id " + 3 + " not found"), HttpStatus.NOT_FOUND));

		assertEquals(this.unidadeRest.consultarUnidadeSaudePorBairro("zepa"), new ResponseEntity<>(us1, HttpStatus.OK));
		assertEquals(this.unidadeRest.consultarUnidadeSaudePorBairro("z"),
				new ResponseEntity(new CustomErrorType("Unidade with bairro z not found"), HttpStatus.NOT_FOUND));
		assertEquals(this.unidadeRest.consultarUnidadeSaudePorBairro("catole"),
				new ResponseEntity<>(us3, HttpStatus.OK));

		assertEquals(this.unidadeRest.consultaEspecialidadeporUnidadeSaude(2),
				new ResponseEntity<>(us2.getEspecialidades(), HttpStatus.OK));

		assertEquals(new ResponseEntity<>(unidades, HttpStatus.OK), this.unidadeRest.getAllUnidades());

	}
}
