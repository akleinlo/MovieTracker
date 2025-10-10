package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public record OmdbSearchResponseDto(
        @JsonProperty("Search")
        List<OmdbMovieShortDto> search
) {

    public List<Movie> toMovieList() {
        if (search == null) return List.of();

        return search.stream()
                .map(s -> new Movie(
                        null,               // id in MongoDB
                        s.title(),
                        s.year(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        s.poster(),
                        "",
                        s.imdbID()
                ))
                .collect(Collectors.toList());
    }
}
