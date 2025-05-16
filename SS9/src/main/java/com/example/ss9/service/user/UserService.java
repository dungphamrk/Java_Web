package com.example.ss9.service.user;


import com.example.ss9.model.User;

public interface UserService {
    User login(String username, String password);
    void register(User user);
}

