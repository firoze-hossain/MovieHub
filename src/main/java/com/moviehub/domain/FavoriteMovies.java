package com.moviehub.domain;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovies {
    private User user;
    private List<Movie> movies = new ArrayList<>();

    public FavoriteMovies(User user) {
        this.user = user;
    }

    public void addMovie(Movie movie) {
        if (user != null) {
            movies.add(movie);
        } else {
            System.out.println("User must be registered to add movies to favorites.");
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
