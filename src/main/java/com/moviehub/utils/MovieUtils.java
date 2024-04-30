package com.moviehub.utils;

import com.moviehub.domain.User;
import com.moviehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MovieUtils {
    @Autowired
    private UserService userService;

    public void runConsoleApplication() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to MovieHub");

        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Register User");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    User user = userService.registerUser(email);
                    System.out.println("User registered successfully with email: " + user.getEmail());
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Exiting MovieHub");
        scanner.close();
    }
}

