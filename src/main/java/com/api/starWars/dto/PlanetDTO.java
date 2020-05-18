package com.api.starWars.dto;

import com.api.starWars.document.Planet;
import com.api.starWars.util.Messages;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class PlanetDTO {

    @NotEmpty(message = Messages.REQUIRED_NAME)
    private String nome;

    @NotEmpty(message = Messages.REQUIRED_CLIMATE)
    private String clima;

    @NotEmpty(message = Messages.REQUIRED_TERRAIN)
    private String terreno;

    private int qtdAparicoesEmFilmes;

    public PlanetDTO() {
    }

    public PlanetDTO(Planet planet) {
        this.nome = planet.getNome();
        this.clima = planet.getClima();
        this.terreno = planet.getTerreno();
        this.qtdAparicoesEmFilmes = planet.getQtdAparicoesEmFilmes();
    }

    public PlanetDTO(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public int getQtdAparicoesEmFilmes() {
        return qtdAparicoesEmFilmes;
    }

    public void setQtdAparicoesEmFilmes(int qtdAparicoesEmFilmes) {
        this.qtdAparicoesEmFilmes = qtdAparicoesEmFilmes;
    }

    public static List<PlanetDTO> fromDto(List<Planet> planets) {
        return planets
                .stream()
                .map(planeta -> new PlanetDTO(planeta))
                .collect(Collectors.toList());
    }

    public static Planet toEntity(PlanetDTO dto) {
        Planet p = new Planet();
        p.setNome(dto.getNome());
        p.setClima(dto.getClima());
        p.setTerreno(dto.getTerreno());
        p.setQtdAparicoesEmFilmes(dto.getQtdAparicoesEmFilmes());
        return p;
    }

}
