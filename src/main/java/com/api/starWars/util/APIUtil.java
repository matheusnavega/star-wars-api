package com.api.starWars.util;

import com.api.starWars.document.Planet;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Utilitário para comunicação com API
 */
public class APIUtil {

    /**
     * Método para configurar header
     * @param url url de conexão com a API
     * @param headerName nome do header no application.yml
     * @param headerValue valor do header no application.yml
     * @return headers
     */
    public static RequestEntity<Void> setHeaderConfiguration(final URI url, String headerName, String headerValue) {
        return RequestEntity
                .get(url)
                .header(headerName, headerValue)
                .accept(MediaType.APPLICATION_JSON)
                .build();
    }

    /**
     * Método para montar a URI de conexão
     * @param url url de conexão com a API
     * @param nome nome do planeta que será pesquisado
     * @return uri completa
     */
    public static URI getURI (String url, String nome) {

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .path("/planets/")
                .queryParam("search", nome)
                .build().toUri();

        return uri;

    }

}
