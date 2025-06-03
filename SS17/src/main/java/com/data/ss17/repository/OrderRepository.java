package com.data.ss17.repository;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveOrder(Order order, List<OrderDetail> orderDetails) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(order);
            for (OrderDetail detail : orderDetails) {
                session.save(detail);
            }
            session.getTransaction().commit();
        }
    }

    public List<Order> getOrdersByCustomerId(Long customerId, int page, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery("FROM Order WHERE customerId = :customerId ORDER BY createdAt DESC", Order.class);
            query.setParameter("customerId", customerId);
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getTotalOrdersByCustomerId(Long customerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Order WHERE customerId = :customerId", Long.class);
            query.setParameter("customerId", customerId);
            return query.uniqueResult();
        }
    }

    public void updateOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        }
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            Query<OrderDetail> query = session.createQuery("FROM OrderDetail WHERE orderId = :orderId", OrderDetail.class);
            query.setParameter("orderId", orderId);
            return query.list();
        }
    }
}