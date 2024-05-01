package com.moviehub.service.implementation;

import com.moviehub.domain.FavoriteMovies;
import com.moviehub.domain.Movie;
import com.moviehub.domain.User;
import com.moviehub.exceptions.NoMoviesFoundException;
import com.moviehub.exceptions.UserNotFoundException;
import com.moviehub.service.FavoriteMoviesService;
import com.moviehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Movie> getFavoriteMoviesWithUser(String userEmail) {
        FavoriteMovies favoriteMovies = userFavorites.get(userEmail);
        if (favoriteMovies == null || favoriteMovies.getMovies().isEmpty()) {
            throw new NoMoviesFoundException("No movies found in favorites for user with email " + userEmail);
        }
        User user = favoriteMovies.getUser();
        List<Movie> movies = favoriteMovies.getMovies();
        System.out.println("User Details:");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Favorite Movies:");
        movies.forEach(movie -> System.out.println("- " + movie.getTitle()));

        return movies;
    }

    @Override
    public void removeMovieFromFavorites(String userEmail, String movieTitleToRemove) {
        FavoriteMovies favoriteMovies = userFavorites.get(userEmail);
        if (favoriteMovies != null) {
            List<Movie> movies = favoriteMovies.getMovies();
            Movie movieToRemove = null;
            for (Movie movie : movies) {
                if (movie.getTitle().equals(movieTitleToRemove)) {
                    movieToRemove = movie;
                    break;
                }
            }
            if (movieToRemove != null) {
                favoriteMovies.removeMovie(movieToRemove);
            } else {
                throw new NoMoviesFoundException("Movie with title '" + movieTitleToRemove + "' not found in favorites for user with email " + userEmail);
            }
        }
    }


}
