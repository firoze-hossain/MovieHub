package com.moviehub.service;

import com.moviehub.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> searchMovies(String query);

    Movie getMovieDetails(String movieTitle);
}
