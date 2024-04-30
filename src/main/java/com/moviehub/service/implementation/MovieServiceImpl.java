package com.moviehub.service.implementation;

import com.moviehub.domain.Movie;
import com.moviehub.service.MovieService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private List<Movie> movies ;

    public MovieServiceImpl(List<Movie> movies) {
        this.movies=movies;
    }

    @Override
    public List<Movie> searchMovies(String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase())
                        || movie.getCast().toLowerCase().contains(query.toLowerCase())
                        || movie.getCategory().toLowerCase().contains(query.toLowerCase()))
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }



    @PostConstruct
    public void movieInit() {
        movies.add(new Movie("The Shawshank Redemption", "Morgan Freeman", "Crime", "1994-10-14", 25000000));
        movies.add(new Movie("The Godfather", "Marlon Brando", "Crime", "1972-03-24", 6000000));
        movies.add(new Movie("The Dark Knight", "Christian Bale", "Action", "2008-07-18", 185000000));
        movies.add(new Movie("Pulp Fiction", "John Travolta", "Crime", "1994-10-14", 8000000));
        movies.add(new Movie("Inception", "Leonardo DiCaprio", "Sci-Fi", "2010-07-16", 160000000));
        movies.add(new Movie("The Matrix", "Keanu Reeves", "Sci-Fi", "1999-03-31", 63000000));
    }
}
