package org.example.backend.controller;

import org.example.backend.model.Movie;
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

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{title}")
    public Movie getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/id/{imdbID}")
    public Movie getMovieByImdbID(@PathVariable String imdbID) {
        return movieService.getMovieByImdbID(imdbID);
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }
    @GetMapping("/trackedMovies")
    public List<Movie> getAllTrackedMovies() {
        return movieService.getAllTrackedMovies();
    }
}
