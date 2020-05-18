package com.api.starWars.service;

import com.api.starWars.dto.StarWarsSearchAPIDTO;

public interface StarWarsService {

    StarWarsSearchAPIDTO buscarDadosStarWarsApi(String nome);

}
