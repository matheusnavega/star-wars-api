package com.api.starWars.service;

import com.api.starWars.document.Planet;
import com.api.starWars.exception.NotFoundError;
import com.api.starWars.repository.PlanetRepository;
import com.api.starWars.service.impl.PlanetServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StarWarsApplicationServiceTests {

    private PlanetService planetService;

    @Autowired
    private PlanetRepository planetRepository;

    @Mock
    private Planet planet;

    @BeforeEach
    public void setUp() {
        planetService = new PlanetServiceImpl(planetRepository);
        planet = new Planet();
        planet.setNome("teste_planeta");
        planet.setClima("teste_clima");
        planet.setTerreno("teste_terreno");
    }

    @Test
    public void testCreateAndDeletePlanet() {
        Assertions.assertTrue(() -> {
			planetRepository.save(planet);
			planetRepository.delete(planet);
            return true;
        });
    }

	@Test
	public void testFindPlanetById() {
		planetRepository.save(planet);
		Assertions.assertTrue(() -> {
			planetService.findById(planet.getId());
			planetRepository.delete(planet);
			return true;
		});
	}

    @Test
    public void testExceptionPlanetIdNotFound() {
        Assertions.assertThrows(NotFoundError.class, () -> {
            planetService.findById("123");
        });
    }

    @Test
    public void testExceptionPlanetNameNotFound() {
        Assertions.assertThrows(NotFoundError.class, () -> {
            planetService.findByName("naoexiste");
        });
    }

}