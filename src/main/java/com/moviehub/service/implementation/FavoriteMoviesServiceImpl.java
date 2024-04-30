package com.moviehub.service.implementation;

import com.moviehub.domain.FavoriteMovies;
import com.moviehub.domain.Movie;
import com.moviehub.domain.User;
import com.moviehub.exceptions.UserNotFoundException;
import com.moviehub.service.FavoriteMoviesService;
import com.moviehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {
    private Map<String, FavoriteMovies> userFavorites = new HashMap<>();
    private UserService userService;

    @Autowired
    public FavoriteMoviesServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addMovieToFavorites(String userEmail, String movieTitle) {
        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException("User with email " + userEmail + " not found");
        }
        FavoriteMovies favoriteMovies = userFavorites.getOrDefault(user.getEmail(), new FavoriteMovies(user));
        favoriteMovies.addMovie(new Movie(movieTitle));
        userFavorites.put(user.getEmail(), favoriteMovies);
    }


}
