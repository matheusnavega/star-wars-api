package com.api.starWars;

import com.api.starWars.controller.PlanetController;
import com.api.starWars.dto.PlanetDTO;
import com.api.starWars.exception.CustomExceptionHandler;
import com.api.starWars.repository.PlanetRepository;
import com.api.starWars.service.PlanetService;
import com.api.starWars.service.impl.PlanetServiceImpl;
import com.api.starWars.util.RouterUtil;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class StarWarsApplicationTests {

	private static final String ID = "1";
	private static final String PLANETA = "Planeta teste";
	private static final String CLIMA = "Clima teste";
	private static final String TERRENO = "Terreno teste";
	private static final String URL_API = "https://swapi.dev/api/";

	@Mock
	PlanetDTO planetDTO;

	@Mock
	PlanetService planetService;

	@InjectMocks
	PlanetController planetController;

	@Mock
	private PlanetRepository planetRepository;

	@Mock
	private CustomExceptionHandler customExceptionHandler;

	@Before
	public void initialiseRestAssuredMockMvcStandalone() {
		RestAssuredMockMvc.standaloneSetup(planetController, customExceptionHandler);
		planetService = new PlanetServiceImpl(planetRepository);
		planetDTO = new PlanetDTO(
				PLANETA,
				CLIMA,
				TERRENO);
	}

	@org.junit.Test
	public void saveShouldReturnStatus201() { //SAVE
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

	@org.junit.Test
	public void removeShouldReturnStatus200() { //DELETE
		String endPoint = RouterUtil.PLANETAS + RouterUtil.FIND_BY_ID;

		given()
				.log().all()
				.contentType(String.valueOf(ContentType.JSON))
				.when()
				.delete(endPoint, ID)
				.then()
				.assertThat().statusCode(200);
	}

	@Test
	public void checkUrlAPI() {

		io.restassured.RestAssured.given()
				.log().all()
				.when()
				.get(URL_API)
				.then()
				.statusCode(200);
	}

}