package com.moviehub.service.implementation;

import com.moviehub.MovieHubApplication;
import com.moviehub.domain.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieServiceImplTest {
    @InjectMocks
    private MovieServiceImpl movieService;
    private static List<Movie> movies;

    @BeforeAll
    public static void setup() {
        System.setProperty("skipConsoleApplication", "true");
        movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption", "Morgan Freeman", "Crime", "1994-10-14", 25000000));
        movies.add(new Movie("The Godfather", "Marlon Brando", "Crime", "1972-03-24", 6000000));
        movies.add(new Movie("The Dark Knight", "Christian Bale", "Action", "2008-07-18", 185000000));
        movies.add(new Movie("Pulp Fiction", "John Travolta", "Crime", "1994-10-14", 8000000));
        movies.add(new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", "2010-07-16", 160000000));
        movies.add(new Movie("The Matrix", "Keanu Reeves", "Sci-Fi", "1999-03-31", 63000000));
    }

    @Test
    @DisplayName("search movie either title, cast or category and return movie ascending order of title")
    public void testSearchMovies() {
        movieService = new MovieServiceImpl(movies);
        List<Movie> result = movieService.searchMovies("Crime");
        assertEquals(3, result.size());
        assertEquals("Pulp Fiction", result.get(0).getTitle());
        assertEquals("The Godfather", result.get(1).getTitle());
        assertEquals("The Shawshank Redemption", result.get(2).getTitle());

    }

    @AfterAll
    public static void shutDown() {
        SpringApplication.exit(SpringApplication.run(MovieHubApplication.class));
    }
}
