package com.api.starWars.service;

import com.api.starWars.document.Planet;

import java.util.List;

public interface PlanetService {

    Planet addPlanet(Planet planet);

    List<Planet> getAll();

    Planet findById(String id);

    Planet findByName(String nome);

    void removePlanet(String id);

    int quantidadeDeAparicoes(String nome);
}
