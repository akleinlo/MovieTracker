package org.example.backend.service;

import org.example.backend.model.OmdbSearchResponseDto;
import org.example.backend.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OmdbApiService {

    private final RestClient restClient;

    @Value("${OMDB_API_KEY}")
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

    public Movie getMovieByImdbID(String imdbID) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("i", imdbID)  // "i" für imdbID in der OMDb API
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
