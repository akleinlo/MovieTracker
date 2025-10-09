package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setup() {
        movieRepository.deleteAll();
    }

    @Test
    void getAllMovies_shouldReturnTwoMovies() throws Exception {
        // GIVEN
        List<Movie> movies = List.of(
                new Movie(
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
                ),
                new Movie(
                        null,
                        "2001: A Space Odyssey",
                        "1968",
                        "06 Apr 1968",
                        "149 min",
                        "Sci-Fi",
                        "Stanley Kubrick",
                        "Stanley Kubrick",
                        "Keir Dullea, Gary Lockwood, William Sylvester",
                        "A space epic about human evolution and AI.",
                        "English",
                        "https://example.com/poster2.jpg",
                        "8.3",
                        "tt0062622")
        );
        movieRepository.saveAll(movies);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                [
                                  {
                                    "title": "Zelig",
                                    "year": "1983",
                                    "released": "25 Aug 1983",
                                    "runtime": "79 min",
                                    "genre": "Comedy",
                                    "director": "Woody Allen",
                                    "writer": "Woody Allen",
                                    "actors": "Woody Allen, Mia Farrow, Patrick Horgan",
                                    "plot": "\\"Documentary\\" about a man who can look and act like whoever he's around, and meets various famous people.",
                                    "language": "English, German",
                                    "poster": "https://m.media-amazon.com/images/M/MV5BMDJiYzNhZjUtNTRmMS00ZjA1LWFmNjUtZjgwYWY2NGYxYzg3XkEyXkFqcGc@._V1_SX300.jpg",
                                    "imdbRating": "7.6",
                                    "imdbID": "tt0086637"
                                  },
                                  {
                                    "title": "2001: A Space Odyssey",
                                    "year": "1968",
                                    "released": "06 Apr 1968",
                                    "runtime": "149 min",
                                    "genre": "Sci-Fi",
                                    "director": "Stanley Kubrick",
                                    "writer": "Stanley Kubrick",
                                    "actors": "Keir Dullea, Gary Lockwood, William Sylvester",
                                    "plot": "A space epic about human evolution and AI.",
                                    "language": "English",
                                    "poster": "https://example.com/poster2.jpg",
                                    "imdbRating": "8.3",
                                    "imdbID": "tt0062622"
                                  }
                                ]
                                """
                ));
    }
}