package com.api.starWars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class StarWarsSearchFilmsAPIDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("films")
    private List<String> filmes;

    public StarWarsSearchFilmsAPIDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<String> filmes) {
        this.filmes = filmes;
    }

}
