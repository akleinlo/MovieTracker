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
               new Movie("1", "Zelig", "Woody Allen"),
               new Movie("2", "2001: A Space Odyssey", "Stanley Kubrick")
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
                                     "id": "1",
                                     "title": "Zelig",
                                     "author": "Woody Allen"
                                     },
                                     {
                                     "id": "2",
                                     "title": "2001: A Space Odyssey",
                                     "author": "Stanley Kubrick"
                                     }
                                    ]
                                   """
                ));
    }
}