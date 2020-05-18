package com.api.starWars.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StarWarsApplicationControllerAPITests {

	private static final String URL_API = "https://swapi.dev/api/";

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