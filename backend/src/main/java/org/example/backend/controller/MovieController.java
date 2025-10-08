package org.example.backend.controller;

import org.example.backend.model.OMDbMovie;
import org.example.backend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{title}")
    public OMDbMovie getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping
    public List<OMDbMovie> getAllMovies() {
        return movieService.getAllMovies();
    }

}
