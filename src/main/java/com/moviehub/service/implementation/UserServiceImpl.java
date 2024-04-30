package com.moviehub.service.implementation;

import com.moviehub.domain.User;
import com.moviehub.exceptions.UserAlreadyExistsException;
import com.moviehub.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private Map<String, User> users = new HashMap<>();

    @Override
    public User registerUser(String email) {
        if (users.containsKey(email)) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }
        User user = new User(email);
        users.put(email, user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }


}
