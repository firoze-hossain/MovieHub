package com.moviehub.service.implementation;

import com.moviehub.domain.User;
import com.moviehub.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User registerUser(String email) {
        return new User(email);
    }
}
