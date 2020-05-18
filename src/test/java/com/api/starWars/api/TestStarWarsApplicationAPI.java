package com.api.starWars.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

/**
 * Teste usando Rest Assured para validar comunicação com API
 */
@SpringBootTest
public class TestStarWarsApplicationAPI {

	private static final String URL_API = "https://swapi.dev/api/";

	@Test
	public void checkUrlAPI() {

		given()
				.log().all()
				.when()
				.get(URL_API)
				.then()
				.statusCode(200);
	}

}