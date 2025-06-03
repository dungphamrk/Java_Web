package com.data.ss17.repository;

import com.data.ss17.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> getProducts(int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getTotalProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Product", Long.class);
            return query.uniqueResult();
        }
    }

    public Product getProductById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE id IN :productIds", Product.class);
            query.setParameter("productIds", productIds);
            return query.list();
        }
    }
}