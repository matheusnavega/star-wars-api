package com.api.starWars.service.impl;

import com.api.starWars.document.Planet;
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

/**
 * Implementacao dos servicos responsaveis por cadastrar, lista e deletar planetas
 */
@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private StarWarsService starWarsService;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    /**
     * Adiciona um planeta
     * @param planet planeta para ser adicionado
     * @return O planet adicionado para exibir seus dados em tela
     * @throws PlanetExistError Se o planeta já existir
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Planet addPlanet(Planet planet) {
        Optional<Planet> planetaOpt = planetRepository.findByNome(planet.getNome());
        if (planetaOpt.isPresent()) {
            throw new PlanetExistError();
        }
        planet.setQtdAparicoesEmFilmes(starWarsService.getNumberOfAppearances(planet.getNome()));
        return planetRepository.save(planet);
    }

    /**
     * Lista todos os planetas
     * @return Lista de planetas
     */
    @Override
    public List<Planet> getAll() {
        return planetRepository.findAll();
    }

    /**
     * Busca planeta pelo id
     * @param id  id do planeta
     * @return O planeta encontrado
     * @throws NotFoundError Se não existir planeta com esse id
     */
    @Override
    public Planet findById(String id) {
        return planetRepository.findById(id)
                .orElseThrow(() -> new NotFoundError());
    }

    /**
     * Busca planeta pelo nome
     * @param nome  id do planeta
     * @return O planeta encontrado
     * @throws NotFoundError Se não existir planeta com esse nome
     */
    @Override
    public Planet findByName(String nome) {
        return planetRepository.findByNome(nome)
                .orElseThrow(() -> new NotFoundError());
    }

    /**
     * Remove planeta pelo id
     * @param id  id do planeta
     * @return Planeta deletado
     * @throws NotFoundError Se não existir planeta com esse id
     */
    @Override
    public void removePlanet(String id) {
        this.findById(id);
        planetRepository.deleteById(id);
    }

}
