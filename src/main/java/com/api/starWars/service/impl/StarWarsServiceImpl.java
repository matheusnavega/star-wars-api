package com.api.starWars.service.impl;

import com.api.starWars.dto.StarWarsSearchAPIDTO;
import com.api.starWars.dto.StarWarsSearchFilmsAPIDTO;
import com.api.starWars.exception.IntegrationError;
import com.api.starWars.service.StarWarsService;
import com.api.starWars.util.APIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class StarWarsServiceImpl implements StarWarsService {

    @Value("${integration.swapi.planets}")
    private String swapiUrl;

    @Value("${integration.headers.name}")
    private String headerName;

    @Value("${integration.headers.value}")
    private String headerValue;

    @Autowired
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Método para buscar a quantidade de aparições em filmes de um planeta
     * @param nome nome do planeta
     * @return quantidade de aparições em filmes de um planeta
     */
    @Override
    public int getNumberOfAppearances(String nome) {
        StarWarsSearchAPIDTO result = this.buscarDadosStarWarsApi(nome);
        List<StarWarsSearchFilmsAPIDTO> results = result.getResults();
        return results.stream()
                .filter(res -> res.getNome().equalsIgnoreCase(nome))
                .mapToInt(res -> res.getFilmes().size())
                .sum();
    }

    /**
     * @param nome nome do planeta
     * @return objeto serializado com os dados encontrados após comunicação com API
     * @exception IntegrationError caso tenha algum problema na API
     */
    @Override
    public StarWarsSearchAPIDTO buscarDadosStarWarsApi(String nome) {

        URI uri = APIUtil.getURI(swapiUrl, nome);

        RequestEntity<?> request = APIUtil.setHeaderConfiguration(uri, headerName, headerValue);
        ResponseEntity<StarWarsSearchAPIDTO> result;
        try {
            result = restTemplate().exchange(uri, HttpMethod.GET, request, StarWarsSearchAPIDTO.class);

        } catch (RestClientException e) {
            throw new IntegrationError();
        }

        return result.getBody();
    }

}
