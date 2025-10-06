package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return List.of(
                new Movie("1", "Zelig", "Woody Allen"),
                new Movie("2", "2001: A Space Odyssey", "Stanley Kubrick")
        );
    }
}
