package org.example.backend.service;

import org.example.backend.config.OmdbApiConfig;
import org.example.backend.model.Movie;
import org.example.backend.model.OmdbSearchResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OmdbApiService {

    private final RestClient restClient;
    private final String apiKey;

    public OmdbApiService(RestClient.Builder restClientBuilder, OmdbApiConfig config) {
        this.restClient = restClientBuilder
                .baseUrl(config.getBaseUrl())
                .build();
        this.apiKey = config.getKey();
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

    public Movie getMovieByImdbID(String imdbID) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("i", imdbID)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(Movie.class);
    }

    public List<Movie> searchMovies(String title) {
        OmdbSearchResponseDto response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("s", title)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(OmdbSearchResponseDto.class);

        return response != null ? response.toMovieList() : List.of();
    }
}
