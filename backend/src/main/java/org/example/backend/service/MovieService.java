package org.example.backend.service;

import org.example.backend.model.OMDbMovie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MovieService {

    private final RestClient restClient;
    private final String apiKey;

    // Konstruktor injiziert den API-Key aus application.properties
    public MovieService(RestClient.Builder restClientBuilder,
                        @Value("${omdb.api.key}") String apiKey) {
        this.restClient = restClientBuilder
                .baseUrl("http://www.omdbapi.com/")
                .build();
        this.apiKey = apiKey;
    }

    public OMDbMovie getMovieByName(String movieName) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("t", movieName)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(OMDbMovie.class);
    }
}
