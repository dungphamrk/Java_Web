package com.data.ss19.repository;

import com.data.ss19.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public List<User> getUsers(int page, int pageSize, String search) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User WHERE 1=1";
            if (search != null && !search.isEmpty()) {
                hql += " AND (LOWER(firstName) LIKE :search OR LOWER(lastName) LIKE :search)";
            }
            Query<User> query = session.createQuery(hql, User.class);
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search.toLowerCase() + "%");
            }
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getTotalUsers(String search) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM User WHERE 1=1";
            if (search != null && !search.isEmpty()) {
                hql += " AND (LOWER(firstName) LIKE :search OR LOWER(lastName) LIKE :search)";
            }
            Query<Long> query = session.createQuery(hql, Long.class);
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search.toLowerCase() + "%");
            }
            return query.uniqueResult();
        }
    }

    public long getTotalUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM User", Long.class);
            return query.uniqueResult();
        }
    }

    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        }
    }
}