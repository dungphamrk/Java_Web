package com.data.ss19.service;

import com.data.ss19.model.User;
import com.data.ss19.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void updateUser(User User) {
        userRepository.updateUser(User);
    }

    public User getUserByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }

    public List<User> getUsers(int page, int pageSize, String search) {
        return userRepository.getUsers(page, pageSize, search);
    }

    public long getTotalUsers(String search) {
        return userRepository.getTotalUsers(search);
    }


    public long getTotalUsers() {
        try (var session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM User", Long.class);
            return query.uniqueResult();
        }
    }
}
