package com.coursework.petition.services;

import com.coursework.petition.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}