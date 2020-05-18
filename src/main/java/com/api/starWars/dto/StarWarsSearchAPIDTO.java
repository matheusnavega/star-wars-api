package com.api.starWars.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsSearchAPIDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("results")
    private List<StarWarsSearchFilmsAPIDTO> results;

    public StarWarsSearchAPIDTO() {
    }

    public List<StarWarsSearchFilmsAPIDTO> getResults() {
        return results;
    }

    public void setResults(List<StarWarsSearchFilmsAPIDTO> results) {
        this.results = results;
    }

}
