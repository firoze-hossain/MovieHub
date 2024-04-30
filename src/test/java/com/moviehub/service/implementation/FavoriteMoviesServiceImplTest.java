package com.moviehub.service.implementation;

import com.moviehub.MovieHubApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FavoriteMoviesServiceImplTest {
    @Mock
    private static FavoriteMoviesServiceImpl favoriteMoviesService;

    @BeforeAll
    public static void setup() {
        System.setProperty("skipConsoleApplication", "true");
    }

    @Test
    public void testAddMovieToFavorites() {
        doNothing().when(favoriteMoviesService).addMovieToFavorites(anyString(), anyString());
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Godfather");
        favoriteMoviesService.addMovieToFavorites("firoze@gmail.com", "The Dark Knight");
        verify(favoriteMoviesService, times(2)).addMovieToFavorites(anyString(), anyString());
    }


    @AfterAll
    public static void shutDown() {
        SpringApplication.exit(SpringApplication.run(MovieHubApplication.class));
    }
}
