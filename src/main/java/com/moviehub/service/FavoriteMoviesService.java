package com.moviehub.service;

import com.moviehub.domain.Movie;

import java.util.List;

public interface FavoriteMoviesService {
    void addMovieToFavorites(String userEmail, String movieTitle);

    List<Movie> getFavoriteMoviesWithUser(String userEmail);

    void removeMovieFromFavorites(String userEmail, String movieTitleToRemove);

}
