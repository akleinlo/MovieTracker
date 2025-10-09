package org.example.backend.service;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final OmdbApiService omdbService;
    private final MovieRepository movieRepository;

    public MovieService(OmdbApiService omdbService, MovieRepository movieRepository) {
        this.omdbService = omdbService;
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByTitle(String title) {
        // Zuerst prüfen, ob der Film schon in der DB ist
        return movieRepository.findByTitle(title)
                .orElseGet(() -> {
                    // Wenn nicht vorhanden, API aufrufen
                    Movie movie = omdbService.getMovieByTitle(title);
                    // In der DB speichern
                    return movieRepository.save(movie);
                });
    }


    public Movie addMovie(Movie movie) {
        return movieRepository.findByImdbID(movie.imdbID())
                .orElseGet(() -> movieRepository.save(movie));
    }

    public List<Movie> searchMovies(String title) {
        // 1. Zuerst DB durchsuchen
        List<Movie> moviesInDb = movieRepository.findByTitleContainingIgnoreCase(title);
        if (!moviesInDb.isEmpty()) {
            return moviesInDb;
        }
        // 2. Wenn nichts gefunden, OMDb API Search aufrufen
        List<Movie> moviesFromApi = omdbService.searchMovies(title);
        return moviesFromApi;
    }

}

