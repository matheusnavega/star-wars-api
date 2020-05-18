package com.api.starWars.util;

import com.api.starWars.document.Planet;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class APIUtil {

    public static Boolean checkQuantityValues(List<Planet> values) {
        Long count = values.stream().count();
        return count >= 1;
    }

    public static RequestEntity<Void> setHeaderConfiguration(final URI url, String headerName, String headerValue) {
        return RequestEntity
                .get(url)
                .header(headerName, headerValue)
                .accept(MediaType.APPLICATION_JSON)
                .build();
    }

    public static URI getURI (String url, String nome) {

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .path("/planets/")
                .queryParam("search", nome)
                .build().toUri();

        return uri;

    }

}
