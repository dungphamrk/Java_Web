package com.data.ss17.repository;

import com.data.ss17.model.ProductCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCartRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<ProductCart> getCartByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductCart> query = session.createQuery("FROM ProductCart WHERE customerId = :customerId", ProductCart.class);
            query.setParameter("customerId", customerId);
            return query.list();
        }
    }

    public ProductCart getCartByCustomerIdAndProductId(Long customerId, Long productId) {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductCart> query = session.createQuery("FROM ProductCart WHERE customerId = :customerId AND productId = :productId", ProductCart.class);
            query.setParameter("customerId", customerId);
            query.setParameter("productId", productId);
            return query.uniqueResult();
        }
    }

    public void addOrUpdateCart(ProductCart cart) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(cart);
            session.getTransaction().commit();
        }
    }

    public void deleteCart(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ProductCart cart = session.get(ProductCart.class, id);
            if (cart != null) {
                session.delete(cart);
            }
            session.getTransaction().commit();
        }
    }
}