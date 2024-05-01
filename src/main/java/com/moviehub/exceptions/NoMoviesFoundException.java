package com.moviehub.exceptions;

public class NoMoviesFoundException extends RuntimeException {
    public NoMoviesFoundException(String message) {
        super(message);
    }
}
