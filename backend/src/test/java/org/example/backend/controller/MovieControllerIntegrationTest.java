package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.example.backend.service.MovieService;
import org.example.backend.service.OmdbApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @MockBean
    private MovieService movieService;

    @MockBean
    private OmdbApiService omdbApiService;

    @Test
    @WithMockUser
    void getAllMovies_shouldReturnListOfMovies_whenGivenTwoMovies() throws Exception {
        // Given
        Movie movie = new Movie(
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

        // Service mocken
        when(movieService.getAllMovies()).thenReturn(List.of(movie));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "testUser"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                          {
                            "id": null,
                            "Title": "Zelig",
                            "Year": "1983",
                            "Released": "25 Aug 1983",
                            "Runtime": "79 min",
                            "Genre": "Comedy",
                            "Director": "Woody Allen",
                            "Writer": "Woody Allen",
                            "Actors": "Woody Allen, Mia Farrow, Patrick Horgan",
                            "Plot": "\\"Documentary\\" about a man who can look and act like whoever he's around, and meets various famous people.",
                            "Language": "English, German",
                            "Poster": "https://m.media-amazon.com/images/M/MV5BMDJiYzNhZjUtNTRmMS00ZjA1LWFmNjUtZjgwYWY2NGYxYzg3XkEyXkFqcGc@._V1_SX300.jpg",
                            "imdbRating": "7.6",
                            "imdbID": "tt0086637"
                          }
                        ]
                        """
                ));

    }
}
