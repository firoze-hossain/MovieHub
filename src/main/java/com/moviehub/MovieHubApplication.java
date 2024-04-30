package com.moviehub;

import com.moviehub.utils.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieHubApplication {
    @Autowired
    private MovieUtils movieUtils;

    public static void main(String[] args) {
        SpringApplication.run(MovieHubApplication.class, args);
    }

    @Bean
    public CommandLineRunner runConsoleApplication() {
        String skipConsoleApplication = System.getProperty("skipConsoleApplication");
        if (skipConsoleApplication == null || !Boolean.parseBoolean(skipConsoleApplication)) {
            return args -> {
                movieUtils.runConsoleApplication();
            };
        } else {
            return args -> {
            };
        }
    }


}
