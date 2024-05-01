package com.moviehub.service.implementation;

import com.moviehub.MovieHubApplication;
import com.moviehub.domain.Movie;
import com.moviehub.exceptions.NoMoviesFoundException;
import com.moviehub.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FavoriteMoviesServiceImplTest {
    @Autowired
    private FavoriteMoviesServiceImpl favoriteMoviesService;

    @Autowired
    private UserService userService;


    @BeforeAll
    public static void setup() {
        System.setProperty("skipConsoleApplication", "true");
    }


    @Test
    public void testAddMovieToFavorites() {
        userService.registerUser("firoze@gmail.com");
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Godfather");
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Dark Knight");
        List<Movie> movies = favoriteMoviesService.getFavoriteMoviesWithUser("firoze@gmail.com");
        assertNotNull(movies);
        assertEquals(2, movies.size());
        assertTrue(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Godfather")));
        assertTrue(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Dark Knight")));
    }


    @Test
    public void testGetFavoriteMoviesWithUser() {
        String userEmail = "firoze@gmail.com";
        userService.registerUser(userEmail);
        favoriteMoviesService.addMovieToFavorites(userEmail, "The Godfather");
        favoriteMoviesService.addMovieToFavorites(userEmail, "The Dark Knight");

        List<Movie> movies = favoriteMoviesService.getFavoriteMoviesWithUser(userEmail);

        assertAll("Verify movies were retrieved correctly",
                () -> assertNotNull(movies),
                () -> assertEquals(2, movies.size()),
                () -> assertTrue(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Godfather"))),
                () -> assertTrue(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Dark Knight")))
        );
    }

    @Test
    public void testRemoveMovieFromFavorites() {
        userService.registerUser("firoze@gmail.com");

        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Godfather");
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Dark Knight");

        favoriteMoviesService.removeMovieFromFavorites("firoze@gmail.com", "The Godfather");

        List<Movie> movies = favoriteMoviesService.getFavoriteMoviesWithUser("firoze@gmail.com");
        assertNotNull(movies);
        assertEquals(1, movies.size());
        assertFalse(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Godfather")));
        assertTrue(movies.stream().anyMatch(movie -> movie.getTitle().equals("The Dark Knight")));
    }

    @Test
    public void testSearchFavoriteMovies() {
        userService.registerUser("firoze@gmail.com");

        assertThrows(NoMoviesFoundException.class, () -> favoriteMoviesService.searchFavoriteMovies("firoze@gmail.com", "Dark"));

        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Godfather");
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Dark Knight");

        List<Movie> foundMovies = favoriteMoviesService.searchFavoriteMovies("firoze@gmail.com", "Dark");
        assertNotNull(foundMovies);
        assertEquals(1, foundMovies.size());
        assertTrue(foundMovies.stream().anyMatch(movie -> movie.getTitle().equals("The Dark Knight")));

        List<Movie> notFoundMovies = favoriteMoviesService.searchFavoriteMovies("firoze@gmail.com", "Spiderman");
        assertNotNull(notFoundMovies);
        assertTrue(notFoundMovies.isEmpty());
    }


    @AfterAll
    public static void shutDown() {
        SpringApplication.exit(SpringApplication.run(MovieHubApplication.class));
    }
}
