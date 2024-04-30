package com.moviehub.service.implementation;

import com.moviehub.domain.FavoriteMovies;
import com.moviehub.domain.Movie;
import com.moviehub.service.FavoriteMoviesService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {
    private Map<String, FavoriteMovies> userFavorites = new HashMap<>();

    @Override
    public void addMovieToFavorites(String userEmail, String movieTitle) {
        FavoriteMovies favoriteMovies = userFavorites.getOrDefault(userEmail, new FavoriteMovies());
        favoriteMovies.addMovie(new Movie(movieTitle));
        userFavorites.put(userEmail, favoriteMovies);
    }

}
