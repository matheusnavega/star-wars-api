package com.api.starWars.repository;

import com.api.starWars.document.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    @Transactional(readOnly = true)
    Optional<Planet> findById(String id);

    @Transactional(readOnly = true)
    Optional<Planet> findByNome(String nome);

}
