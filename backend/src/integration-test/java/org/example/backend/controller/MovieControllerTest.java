package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Test
    void getAllMovies_shouldReturnTwoMovies_whenGivenTwoMovies() {
        // GIVEN
        List<Movie> movies = List.of(
                new Movie("1", "Zelig", "Woody Allen"),
                new Movie("2", "2001: A Space Odyssey", "Stanley Kubrick")
                );

        MovieRepository mockRepo = mock(MovieRepository.class);
        when(mockRepo.findAll()).thenReturn(movies);

        MovieController controller = new MovieController(mockRepo);

        // WHEN
        List<Movie> actual = controller.getAllMovies();

        // THEN
        assertEquals(movies, actual);
        //verify(mockRepo).findAll();
    }

    @Test
    void getAllMovies() {
    }
}