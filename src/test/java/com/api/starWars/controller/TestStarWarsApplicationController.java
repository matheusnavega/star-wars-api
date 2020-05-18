package com.api.starWars.controller;

import com.api.starWars.dto.PlanetDTO;
import com.api.starWars.exception.CustomExceptionHandler;
import com.api.starWars.service.PlanetService;
import com.api.starWars.util.RouterUtil;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

/**
 * Teste usando Rest Assured com objetos mockados para validar os endpoints no controller
 */
@SpringBootTest
public class TestStarWarsApplicationController {

	private static final String ID = "1";
	private static final String PLANETA = "Planeta teste";
	private static final String CLIMA = "Clima teste";
	private static final String TERRENO = "Terreno teste";

	@Mock
	PlanetDTO planetDTO;

	@Mock
	PlanetService planetService;

	@InjectMocks
	PlanetController planetController;

	@Mock
	private CustomExceptionHandler customExceptionHandler;

	@BeforeEach
	public void initialiseRestAssuredMockMvcStandalone() {
		RestAssuredMockMvc.standaloneSetup(planetController, customExceptionHandler);
		planetDTO = new PlanetDTO(
				PLANETA,
				CLIMA,
				TERRENO);
	}

	@Test
	public void testMockCreateAPlanet() {
		String endPoint = RouterUtil.PLANETAS;

		given()
				.log().all()
				.contentType(String.valueOf(ContentType.JSON))
				.body(planetDTO)
				.when()
				.post(endPoint)
				.then()
				.assertThat().statusCode(201);
	}

	@Test
	public void testMockCreateAPlanetWithoutBody() {
		String endPoint = RouterUtil.PLANETAS;

		given()
				.log().all()
				.contentType(String.valueOf(ContentType.JSON))
				.when()
				.post(endPoint)
				.then()
				.assertThat().statusCode(400);
	}

	@Test
	public void testMockListAllPlanets() {
		String endPoint = RouterUtil.PLANETAS;

		given()
				.log().all()
				.contentType(String.valueOf(ContentType.JSON))
				.when()
				.get(endPoint)
				.then()
				.assertThat().statusCode(200);
	}

	@Test
	public void testMockDeleteAPlanet() {
		String endPoint = RouterUtil.PLANETAS + RouterUtil.FIND_BY_ID;

		given()
				.log().all()
				.contentType(String.valueOf(ContentType.JSON))
				.when()
				.delete(endPoint, ID)
				.then()
				.assertThat().statusCode(204);
	}
}