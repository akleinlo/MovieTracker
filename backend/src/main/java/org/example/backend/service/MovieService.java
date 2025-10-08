package org.example.backend.service;

import org.example.backend.model.OMDbMovie;
import org.example.backend.repository.OMDbMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final OMDbService omdbService;
    private final OMDbMovieRepository movieRepository;

    public MovieService(OMDbService omdbService, OMDbMovieRepository movieRepository) {
        this.omdbService = omdbService;
        this.movieRepository = movieRepository;
    }

    public List<OMDbMovie> getAllMovies() {
        return movieRepository.findAll();
    }

    public OMDbMovie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(movie -> {
                    System.out.println("🎬 Found movie in MongoDB: " + movie.title());
                    return movie;
                })


                .orElseGet(() -> {
                    System.out.println("🌐 Fetching movie from OMDb API: " + title);
                    OMDbMovie movie = omdbService.getMovieByTitle(title);
                    movieRepository.save(movie);
                    return movie;
                });
    }
}

