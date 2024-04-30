package com.moviehub.service;

import com.moviehub.domain.User;

public interface UserService {
    User registerUser(String email);

    User getUserByEmail(String email);
}
