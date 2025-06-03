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

    public Product getProductById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public void saveProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public void deleteProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.list();
        }
    }

    public List<Product> getProducts(int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product ORDER BY id", Product.class);
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE id IN :ids", Product.class);
            query.setParameter("ids", ids);
            return query.list();
        }
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice, Integer minStock, int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("FROM Product WHERE 1=1");
            if (name != null && !name.isEmpty()) {
                hql.append(" AND LOWER(productName) LIKE :name");
            }
            if (minPrice != null) {
                hql.append(" AND price >= :minPrice");
            }
            if (maxPrice != null) {
                hql.append(" AND price <= :maxPrice");
            }
            if (minStock != null) {
                hql.append(" AND stock >= :minStock");
            }
            hql.append(" ORDER BY id");

            Query<Product> query = session.createQuery(hql.toString(), Product.class);
            if (name != null && !name.isEmpty()) {
                query.setParameter("name", "%" + name.toLowerCase() + "%");
            }
            if (minPrice != null) {
                query.setParameter("minPrice", minPrice);
            }
            if (maxPrice != null) {
                query.setParameter("maxPrice", maxPrice);
            }
            if (minStock != null) {
                query.setParameter("minStock", minStock);
            }
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}