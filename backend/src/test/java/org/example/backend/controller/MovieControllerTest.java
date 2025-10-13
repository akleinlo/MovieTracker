package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.service.MovieService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Test
    void getAllMovies_shouldReturnTwoMovies_whenGivenTwoMovies() {
        // GIVEN
        List<Movie> movies = List.of(
                new Movie(null, "Zelig", "1983", "25 Aug 1983", "79 min", "Comedy",
                        "Woody Allen", "Woody Allen",
                        "Woody Allen, Mia Farrow, Patrick Horgan",
                        "\"Documentary\" about a man who can look and act like whoever he's around, and meets various famous people.",
                        "English, German",
                        "https://m.media-amazon.com/images/M/MV5BMDJiYzNhZjUtNTRmMS00ZjA1LWFmNjUtZjgwYWY2NGYxYzg3XkEyXkFqcGc@._V1_SX300.jpg",
                        "7.6",
                        "tt0086637"),
                new Movie(null, "2001: A Space Odyssey", "1968", "06 Apr 1968", "149 min", "Sci-Fi",
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
        List<Movie> actual = controller.getAllMovies();

        // THEN
        assertEquals(movies, actual);
        verify(mockService, times(1)).getAllMovies();
    }

    @Test
    void getMovieByTitle_shouldReturnMovie_whenGivenTitle() {
        // GIVEN
        String title = "Zelig";
        Movie mockedMovie = new Movie(
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
        Movie actual = controller.getMovieByTitle(title);

        // THEN
        assertEquals(mockedMovie, actual);
        verify(mockService, times(1)).getMovieByTitle(title);
    }

    @Test
    void getMovieByImdbID_shouldReturnMovie_whenGivenImdbID() {
        // GIVEN
        String imdbID = "tt1375666";
        Movie mockedMovie = new Movie(
                "123",
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

        MovieService mockService = mock(MovieService.class);
        when(mockService.getMovieByImdbID(imdbID)).thenReturn(mockedMovie);

        MovieController controller = new MovieController(mockService);

        // WHEN
        Movie actual = controller.getMovieByImdbID(imdbID);

        // THEN
        assertEquals(mockedMovie, actual);
        verify(mockService, times(1)).getMovieByImdbID(imdbID);
    }

    @Test
    void searchMovies_shouldReturnList_whenGivenTitle() {
        // GIVEN
        String searchTitle = "Inception";
        List<Movie> mockedList = List.of(
                new Movie("123",
                        "Inception",
                        "2010",
                        "16 Jul 2010",
                        "148 min",
                        "Sci-Fi",
                        "Christopher Nolan", "Christopher Nolan",
                        "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                        "A thief who steals corporate secrets through use of dream-sharing technology.",
                        "English",
                        "https://example.com/inception.jpg",
                        "8.8",
                        "tt1375666")
        );

        MovieService mockService = mock(MovieService.class);
        when(mockService.searchMovies(searchTitle)).thenReturn(mockedList);

        MovieController controller = new MovieController(mockService);

        // WHEN
        List<Movie> actual = controller.searchMovies(searchTitle);

        // THEN
        assertEquals(mockedList, actual);
        verify(mockService, times(1)).searchMovies(searchTitle);
    }

    @Test
    void getAllTrackedMovies_shouldReturnAllMovies() {
        // GIVEN
        List<Movie> trackedMovies = List.of(
                new Movie("123", "Inception", "2010", "16 Jul 2010", "148 min", "Sci-Fi",
                        "Christopher Nolan", "Christopher Nolan",
                        "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                        "A thief who steals corporate secrets through use of dream-sharing technology.",
                        "English",
                        "https://example.com/inception.jpg",
                        "8.8",
                        "tt1375666"),
                new Movie("124", "Zelig", "1983", "25 Aug 1983", "79 min", "Comedy",
                        "Woody Allen", "Woody Allen",
                        "Woody Allen, Mia Farrow, Patrick Horgan",
                        "\"Documentary\" about a man who can look and act like whoever he's around.",
                        "English, German",
                        "https://example.com/zelig.jpg",
                        "7.6",
                        "tt0086637")
        );

        MovieService mockService = mock(MovieService.class);
        when(mockService.getAllTrackedMovies()).thenReturn(trackedMovies);

        MovieController controller = new MovieController(mockService);

        // WHEN
        List<Movie> actual = controller.getAllTrackedMovies();

        // THEN
        assertEquals(trackedMovies, actual);
        verify(mockService, times(1)).getAllTrackedMovies();
    }



    @Test
    void addMovie_shouldCallServiceAndReturnSavedMovie() {
        // GIVEN
        Movie newMovie = new Movie(null, "Inception", "2010", "16 Jul 2010", "148 min", "Sci-Fi",
                "Christopher Nolan", "Christopher Nolan",
                "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                "A thief who steals corporate secrets through use of dream-sharing technology.",
                "English",
                "https://example.com/inception.jpg",
                "8.8",
                "tt1375666");

        Movie savedMovie = new Movie("123", "Inception", "2010", "16 Jul 2010", "148 min", "Sci-Fi",
                "Christopher Nolan", "Christopher Nolan",
                "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                "A thief who steals corporate secrets through use of dream-sharing technology.",
                "English",
                "https://example.com/inception.jpg",
                "8.8",
                "tt1375666");

        MovieService mockService = mock(MovieService.class);
        when(mockService.addMovie(newMovie)).thenReturn(savedMovie);

        MovieController controller = new MovieController(mockService);

        // WHEN
        Movie actual = controller.addMovie(newMovie);

        // THEN
        assertEquals(savedMovie, actual);
        verify(mockService, times(1)).addMovie(newMovie);
    }

    @Test
    void deleteMovieById_shouldCallServiceOnce() {
        // GIVEN
        String id = "abc123";
        MovieService mockService = mock(MovieService.class);
        MovieController controller = new MovieController(mockService);

        // WHEN
        controller.deleteMovieById(id);

        // THEN
        verify(mockService, times(1)).deleteMovieById(id);
    }

    @Test
    void deleteMovieByImdbID_shouldCallServiceOnce() {
        // GIVEN
        String imdbId = "abc123";
        MovieService mockService = mock(MovieService.class);
        MovieController controller = new MovieController(mockService);

        // WHEN
        controller.deleteMovieByImdbID(imdbId);

        // THEN
        verify(mockService, times(1)).deleteMovieByImdbID(imdbId);
    }

    @Test
    void deleteMovieByTitle_shouldCallServiceOnce() {
        // GIVEN
        String title = "Inception";
        MovieService mockService = mock(MovieService.class);
        MovieController controller = new MovieController(mockService);

        // WHEN
        controller.deleteMovieByTitle(title);

        // THEN
        verify(mockService, times(1)).deleteMovieByTitle(title);
    }

    @Test
    void deleteAllMovies_shouldCallServiceOnce() {
        // GIVEN
        MovieService mockService = mock(MovieService.class);
        MovieController controller = new MovieController(mockService);

        // WHEN
        controller.deleteAllMovies();

        // THEN
        verify(mockService, times(1)).deleteAllMovies();
    }


}