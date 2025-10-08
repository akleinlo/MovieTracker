package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.model.OMDbMovie;
import org.example.backend.repository.MovieRepository;
import org.example.backend.service.MovieService;
import org.example.backend.service.OMDbService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Test
    void getAllMovies_shouldReturnTwoMovies_whenGivenTwoMovies() {
        // GIVEN
        List<OMDbMovie> movies = List.of(
                new OMDbMovie(null, "Zelig", "1983", "25 Aug 1983", "79 min", "Comedy",
                        "Woody Allen", "Woody Allen",
                        "Woody Allen, Mia Farrow, Patrick Horgan",
                        "\"Documentary\" about a man who can look and act like whoever he's around, and meets various famous people.",
                        "English, German",
                        "https://m.media-amazon.com/images/M/MV5BMDJiYzNhZjUtNTRmMS00ZjA1LWFmNjUtZjgwYWY2NGYxYzg3XkEyXkFqcGc@._V1_SX300.jpg",
                        "7.6",
                        "tt0086637"),
                new OMDbMovie(null, "2001: A Space Odyssey", "1968", "06 Apr 1968", "149 min", "Sci-Fi",
                        "Stanley Kubrick", "Stanley Kubrick",
                        "Keir Dullea, Gary Lockwood, William Sylvester",
                        "A space epic about human evolution and AI.",
                        "English",
                        "https://example.com/poster2.jpg",
                        "8.3",
                        "tt0062622")
        );

        MovieService mockService = mock(MovieService.class);
        when(mockService.getAllMovies()).thenReturn(movies);

        MovieController controller = new MovieController(mockService);

        // WHEN
        List<OMDbMovie> actual = controller.getAllMovies();

        // THEN
        assertEquals(movies, actual);
        verify(mockService, times(1)).getAllMovies();
    }

    @Test
    void getMovieByTitle_shouldReturnMovie_whenGivenTitle() {
        // GIVEN
        String title = "Zelig";
        OMDbMovie mockedMovie = new OMDbMovie(
                null,
                "Zelig",
                "1983",
                "25 Aug 1983",
                "79 min",
                "Comedy",
                "Woody Allen",
                "Woody Allen",
                "Woody Allen, Mia Farrow, Patrick Horgan",
                "\"Documentary\" about a man who can look and act like whoever he's around, and meets various famous people.",
                "English, German",
                "https://m.media-amazon.com/images/M/MV5BMDJiYzNhZjUtNTRmMS00ZjA1LWFmNjUtZjgwYWY2NGYxYzg3XkEyXkFqcGc@._V1_SX300.jpg",
                "7.6",
                "tt0086637"
        );

        MovieService mockService = mock(MovieService.class);
        when(mockService.getMovieByTitle(title)).thenReturn(mockedMovie);

        MovieController controller = new MovieController(mockService);

        // WHEN
        OMDbMovie actual = controller.getMovieByTitle(title);

        // THEN
        assertEquals(mockedMovie, actual);
        verify(mockService, times(1)).getMovieByTitle(title);
    }
}