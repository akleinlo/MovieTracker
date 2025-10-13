package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.example.backend.service.MovieService;
import org.example.backend.service.OmdbApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    private MovieRepository movieRepository;
    private OmdbApiService omdbService;
    private MovieService movieService;

    private Movie sampleMovie;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        omdbService = mock(OmdbApiService.class);
        movieService = new MovieService(omdbService, movieRepository);

        sampleMovie = new Movie(
                "1",
                "Inception",
                "2010",
                "16 Jul 2010",
                "148 min",
                "Sci-Fi",
                "Christopher Nolan",
                "Christopher Nolan",
                "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                "A thief who steals corporate secrets through use of dream-sharing technology.",
                "English",
                "https://example.com/inception.jpg",
                "8.8",
                "tt1375666"
        );
    }

    // 1. getAllMovies
    @Test
    void getAllMovies_shouldReturnMoviesFromRepository() {
        List<Movie> movies = List.of(sampleMovie);
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(movies, result);
        verify(movieRepository, times(1)).findAll();
    }

    // 2. getMovieByTitle
    @Test
    void getMovieByTitle_shouldReturnFromRepositoryIfExists() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(sampleMovie));

        Movie result = movieService.getMovieByTitle("Inception");

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).findByTitle("Inception");
    }

    @Test
    void getMovieByTitle_shouldFetchFromOmdbIfNotInRepository() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.empty());
        when(omdbService.getMovieByTitle("Inception")).thenReturn(sampleMovie);

        Movie result = movieService.getMovieByTitle("Inception");

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).findByTitle("Inception");
        verify(omdbService, times(1)).getMovieByTitle("Inception");
    }

    @Test
    void getMovieByTitle_shouldThrowIfNotFound() {
        when(movieRepository.findByTitle("Unknown")).thenReturn(Optional.empty());
        when(omdbService.getMovieByTitle("Unknown")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> movieService.getMovieByTitle("Unknown"));
        assertTrue(exception.getMessage().contains("Movie not found"));
    }

    // 3. getMovieByImdbID
    @Test
    void getMovieByImdbID_shouldReturnFromRepositoryIfExists() {
        when(movieRepository.findByImdbID("tt1375666")).thenReturn(Optional.of(sampleMovie));

        Movie result = movieService.getMovieByImdbID("tt1375666");

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).findByImdbID("tt1375666");
    }

    @Test
    void getMovieByImdbID_shouldFetchFromOmdbIfNotInRepository() {
        when(movieRepository.findByImdbID("tt1375666")).thenReturn(Optional.empty());
        when(omdbService.getMovieByImdbID("tt1375666")).thenReturn(sampleMovie);

        Movie result = movieService.getMovieByImdbID("tt1375666");

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).findByImdbID("tt1375666");
        verify(omdbService, times(1)).getMovieByImdbID("tt1375666");
    }

    // 4. searchMovies
    @Test
    void searchMovies_shouldReturnFromRepositoryIfNotEmpty() {
        List<Movie> movies = List.of(sampleMovie);
        when(movieRepository.findByTitleContainingIgnoreCase("Inception")).thenReturn(movies);

        List<Movie> result = movieService.searchMovies("Inception");

        assertEquals(movies, result);
        verify(movieRepository, times(1)).findByTitleContainingIgnoreCase("Inception");
    }

    @Test
    void searchMovies_shouldCallOmdbIfRepositoryEmpty() {
        when(movieRepository.findByTitleContainingIgnoreCase("Inception")).thenReturn(List.of());
        List<Movie> omdbResult = List.of(sampleMovie);
        when(omdbService.searchMovies("Inception")).thenReturn(omdbResult);

        List<Movie> result = movieService.searchMovies("Inception");

        assertEquals(omdbResult, result);
        verify(movieRepository, times(1)).findByTitleContainingIgnoreCase("Inception");
        verify(omdbService, times(1)).searchMovies("Inception");
    }

    // 5. addMovie
    @Test
    void addMovie_shouldReturnExistingIfExists() {
        when(movieRepository.findByImdbID(sampleMovie.imdbID())).thenReturn(Optional.of(sampleMovie));

        Movie result = movieService.addMovie(sampleMovie);

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).findByImdbID(sampleMovie.imdbID());
    }

    @Test
    void addMovie_shouldSaveIfNotExists() {
        when(movieRepository.findByImdbID(sampleMovie.imdbID())).thenReturn(Optional.empty());
        when(movieRepository.save(sampleMovie)).thenReturn(sampleMovie);

        Movie result = movieService.addMovie(sampleMovie);

        assertEquals(sampleMovie, result);
        verify(movieRepository, times(1)).save(sampleMovie);
    }

    // 6. getAllTrackedMovies
    @Test
    void getAllTrackedMovies_shouldCallRepository() {
        List<Movie> movies = List.of(sampleMovie);
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllTrackedMovies();

        assertEquals(movies, result);
        verify(movieRepository, times(1)).findAll();
    }

    // 7. deleteMovieById
    @Test
    void deleteMovieById_shouldDeleteIfExists() {
        when(movieRepository.existsById("1")).thenReturn(true);

        movieService.deleteMovieById("1");

        verify(movieRepository, times(1)).deleteById("1");
    }

    @Test
    void deleteMovieById_shouldThrowIfNotExists() {
        when(movieRepository.existsById("1")).thenReturn(false);

        RuntimeException e = assertThrows(RuntimeException.class, () -> movieService.deleteMovieById("1"));
        assertTrue(e.getMessage().contains("not found"));
    }

    // 8. deleteMovieByImdbID
    @Test
    void deleteMovieByImdbID_shouldDeleteIfExists() {
        when(movieRepository.findByImdbID("tt1375666")).thenReturn(Optional.of(sampleMovie));

        movieService.deleteMovieByImdbID("tt1375666");

        verify(movieRepository, times(1)).delete(sampleMovie);
    }

    @Test
    void deleteMovieByImdbID_shouldThrowIfNotExists() {
        when(movieRepository.findByImdbID("tt1375666")).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> movieService.deleteMovieByImdbID("tt1375666"));
        assertTrue(e.getMessage().contains("not found"));
    }

    // 9. deleteMovieByTitle
    @Test
    void deleteMovieByTitle_shouldDeleteIfExists() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(sampleMovie));

        movieService.deleteMovieByTitle("Inception");

        verify(movieRepository, times(1)).delete(sampleMovie);
    }

    @Test
    void deleteMovieByTitle_shouldThrowIfNotExists() {
        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.empty());

        RuntimeException e = assertThrows(RuntimeException.class,
                () -> movieService.deleteMovieByTitle("Inception"));
        assertTrue(e.getMessage().contains("not found"));
    }

    // 10. deleteAllMovies
    @Test
    void deleteAllMovies_shouldCallRepository() {
        movieService.deleteAllMovies();

        verify(movieRepository, times(1)).deleteAll();
    }


}
