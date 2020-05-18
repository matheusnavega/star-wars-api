package com.api.starWars.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class StarWarsApplicationAPITests {

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