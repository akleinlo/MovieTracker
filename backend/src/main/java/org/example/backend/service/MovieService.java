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
        return movieRepository.findByTitle(title)
                .orElseGet(() -> {
                    Movie movie = omdbService.getMovieByTitle(title);

                    if (movie == null || movie.imdbID() == null || movie.imdbID().isEmpty()) {
                        throw new RuntimeException("Movie not found: " + title);
                    }

                    return movie;
                });
    }

    public Movie getMovieByImdbID(String imdbID) {
        return movieRepository.findByImdbID(imdbID)
                .orElseGet(() -> omdbService.getMovieByImdbID(imdbID));
    }

    public List<Movie> searchMovies(String title) {
        List<Movie> moviesInDb = movieRepository.findByTitleContainingIgnoreCase(title);
        if (!moviesInDb.isEmpty()) {
            return moviesInDb;
        }
        return omdbService.searchMovies(title);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.findByImdbID(movie.imdbID())
                .orElseGet(() -> movieRepository.save(movie));
    }

    public List<Movie> getAllTrackedMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovieById(String id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }

    public void deleteMovieByImdbID(String imdbID) {
        movieRepository.findByImdbID(imdbID)
                .ifPresentOrElse(
                        movieRepository::delete,
                        () -> {
                            throw new RuntimeException("Movie with IMDb ID " + imdbID + " not found");
                        }
                );
    }

    public void deleteMovieByTitle(String title) {
        movieRepository.findByTitle(title)
                .ifPresentOrElse(
                        movieRepository::delete,
                        () -> {
                            throw new RuntimeException("Movie with title " + title + " not found");
                        }
                );
    }

    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }
}
