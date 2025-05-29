package com.data.ss14.repository.B7;

import com.data.ss14.model.B7.User;

public interface UserDAO {
    User findByUsernameAndPassword(String username, String password);
}

