package org.example.backend.model;

public record OMDbMovie(
        String Title,
        String Year,
        String Released,
        String Runtime,
        String Genre,
        String Director,
        String Writer,
        String Actors,
        String Plot,
        String Language,
        String Poster,
        String imdbRating,
        String imdbID
) {
}
