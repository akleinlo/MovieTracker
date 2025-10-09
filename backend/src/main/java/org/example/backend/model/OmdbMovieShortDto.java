package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OmdbMovieShortDto(
        @JsonProperty("Title")
        String title,
        @JsonProperty("Year")
        String year,
        @JsonProperty("imdbID")
        String imdbID,
        @JsonProperty("Poster")
        String poster
) {
}
