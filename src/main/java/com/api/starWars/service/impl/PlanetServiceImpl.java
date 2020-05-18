package com.api.starWars.service.impl;

import com.api.starWars.document.Planet;
import com.api.starWars.dto.StarWarsSearchAPIDTO;
import com.api.starWars.dto.StarWarsSearchFilmsAPIDTO;
import com.api.starWars.exception.NotFoundError;
import com.api.starWars.exception.PlanetExistError;
import com.api.starWars.repository.PlanetRepository;
import com.api.starWars.service.PlanetService;
import com.api.starWars.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private StarWarsService starWarsService;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Planet addPlanet(Planet planet) {
        Optional<Planet> planetaOpt = planetRepository.findByNome(planet.getNome());
        if (planetaOpt.isPresent()) {
            throw new PlanetExistError();
        }
        planet.setQtdAparicoesEmFilmes(getNumberOfAppearances(planet.getNome()));
        return planetRepository.save(planet);
    }

    @Override
    public List<Planet> getAll() {
        return planetRepository.findAll();
    }

    @Override
    public Planet findById(String id) {
        return planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundError());
    }

    @Override
    public Planet findByName(String nome) {
        return planetRepository.findByNome(nome)
                .orElseThrow(() -> new NotFoundError());
    }

    @Override
    public void removePlanet(String id) {
        this.findById(id);
        planetRepository.deleteById(id);
    }

    @Override
    public int getNumberOfAppearances(String nome) {
        StarWarsSearchAPIDTO result = starWarsService.buscarDadosStarWarsApi(nome);
        List<StarWarsSearchFilmsAPIDTO> results = result.getResults();
        return results.stream()
                .filter(res -> res.getNome().equalsIgnoreCase(nome))
                .mapToInt(res -> res.getFilmes().size())
                .sum();
    }

}
