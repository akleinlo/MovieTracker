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
                .map(movie -> {
                    System.out.println("🎬 Found movie in MongoDB: " + movie.title());
                    return movie;
                })


                .orElseGet(() -> {
                    System.out.println("🌐 Fetching movie from OMDb API: " + title);
                    Movie movie = omdbService.getMovieByTitle(title);
                    movieRepository.save(movie);
                    return movie;
                });
    }
}

