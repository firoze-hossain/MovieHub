package com.moviehub.utils;

import com.moviehub.domain.Movie;
import com.moviehub.domain.User;
import com.moviehub.exceptions.NoMoviesFoundException;
import com.moviehub.exceptions.UserNotFoundException;
import com.moviehub.service.FavoriteMoviesService;
import com.moviehub.service.MovieService;
import com.moviehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MovieUtils {
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private FavoriteMoviesService favoriteMoviesService;

    public void runConsoleApplication() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            System.out.println("Welcome to MovieHub");

            while (!exit) {
                System.out.println("Select an option:");
                System.out.println("1. Register User");
                System.out.println("2. Search Movies");
                System.out.println("3. Get Movie Details");
                System.out.println("4. Add Movie to Favorites");
                System.out.println("5. Get Favorite Movies With User Details");
                System.out.println("6. Exit");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter your email: ");
                        String email = scanner.nextLine();
                        User user = userService.registerUser(email);
                        System.out.println("User registered successfully with email: " + user.getEmail());
                        break;
                    case 2:
                        System.out.print("Enter search query: ");
                        String query = scanner.nextLine();
                        List<Movie> movies = movieService.searchMovies(query);
                        System.out.println("Matching movies:");
                        movies.forEach(movie -> System.out.println(movie.getTitle()));
                        break;
                    case 3:
                        System.out.print("Enter movie title: ");
                        String title = scanner.nextLine();
                        Movie movie = movieService.getMovieDetails(title);
                        if (movie != null) {
                            System.out.println("Title: " + movie.getTitle());
                            System.out.println("Cast: " + movie.getCast());
                            System.out.println("Category: " + movie.getCategory());
                            System.out.println("Release Date: " + movie.getReleaseDate());
                            System.out.println("Budget: " + movie.getBudget());
                        } else {
                            System.out.println("Movie not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter your email: ");
                        String userEmail = scanner.nextLine();
                        System.out.print("Enter movie title: ");
                        String movieTitleToAdd = scanner.nextLine();
                        try {
                            favoriteMoviesService.addMovieToFavorites(userEmail, movieTitleToAdd);
                            System.out.println("Movie added to favorites.");
                        } catch (UserNotFoundException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.print("Enter your email: ");
                        String userEmailForFavorites = scanner.nextLine();
                        try {
                            List<Movie> favoriteMovies = favoriteMoviesService.getFavoriteMoviesWithUser(userEmailForFavorites);
                        } catch (NoMoviesFoundException e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }

                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            System.out.println("Exiting MovieHub");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

