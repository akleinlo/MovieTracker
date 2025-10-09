package org.example.backend.service;

import org.example.backend.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OmdbApiService {

    private final RestClient restClient;

    @Value("${omdb.api.key}")
    private String apiKey;

    public OmdbApiService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://www.omdbapi.com/")
                .build();
    }
    public Movie getMovieByTitle(String title) {
        return restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("t", title)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve()
                        .body(Movie.class);
    }
}
