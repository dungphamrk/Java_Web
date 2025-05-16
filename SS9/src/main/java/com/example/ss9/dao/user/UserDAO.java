package com.example.ss9.dao.user;


import com.example.ss9.model.User;

public interface UserDAO {
    User findUserByUsernameAndPassword(String username, String password);
    void addUser(User user);
}
