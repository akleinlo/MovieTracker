package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public record Movie(
        @Id
        String id,

        @JsonProperty("Title")
        String title,

        @JsonProperty("Year")
        String year,

        @JsonProperty("Released")
        String released,

        @JsonProperty("Runtime")
        String runtime,

        @JsonProperty("Genre")
        String genre,

        @JsonProperty("Director")
        String director,

        @JsonProperty("Writer")
        String writer,

        @JsonProperty("Actors")
        String actors,

        @JsonProperty("Plot")
        String plot,

        @JsonProperty("Language")
        String language,

        @JsonProperty("Poster")
        String poster,

        @JsonProperty("imdbRating")
        String imdbRating,

        @JsonProperty("imdbID")
        String imdbID
) {}
