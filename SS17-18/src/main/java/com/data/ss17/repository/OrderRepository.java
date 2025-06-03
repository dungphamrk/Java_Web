package com.data.ss17.repository;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
                detail.setOrderId(order.getId());
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

    public Order getOrderById(Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, orderId);
        }
    }

    public List<Order> getOrders(int page, int pageSize, String recipientName, LocalDate startDate, LocalDate endDate, String status) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("FROM Order WHERE 1=1");
            if (recipientName != null && !recipientName.isEmpty()) {
                hql.append(" AND LOWER(recipientName) LIKE :recipientName");
            }
            if (startDate != null) {
                hql.append(" AND createdAt >= :startDate");
            }
            if (endDate != null) {
                hql.append(" AND createdAt <= :endDate");
            }
            if (status != null && !status.isEmpty()) {
                hql.append(" AND status = :status");
            }
            hql.append(" ORDER BY createdAt DESC");

            Query<Order> query = session.createQuery(hql.toString(), Order.class);
            if (recipientName != null && !recipientName.isEmpty()) {
                query.setParameter("recipientName", "%" + recipientName.toLowerCase() + "%");
            }
            if (startDate != null) {
                query.setParameter("startDate", startDate);
            }
            if (endDate != null) {
                query.setParameter("endDate", endDate);
            }
            if (status != null && !status.isEmpty()) {
                query.setParameter("status", status);
            }
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getTotalOrders(String recipientName, LocalDate startDate, LocalDate endDate, String status) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM Order WHERE 1=1");
            if (recipientName != null && !recipientName.isEmpty()) {
                hql.append(" AND LOWER(recipientName) LIKE :recipientName");
            }
            if (startDate != null) {
                hql.append(" AND createdAt >= :startDate");
            }
            if (endDate != null) {
                hql.append(" AND createdAt <= :endDate");
            }
            if (status != null && !status.isEmpty()) {
                hql.append(" AND status = :status");
            }

            Query<Long> query = session.createQuery(hql.toString(), Long.class);
            if (recipientName != null && !recipientName.isEmpty()) {
                query.setParameter("recipientName", "%" + recipientName.toLowerCase() + "%");
            }
            if (startDate != null) {
                query.setParameter("startDate", startDate);
            }
            if (endDate != null) {
                query.setParameter("endDate", endDate);
            }
            if (status != null && !status.isEmpty()) {
                query.setParameter("status", status);
            }
            return query.uniqueResult();
        }
    }

    public long getTotalOrders() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Order", Long.class);
            return query.uniqueResult();
        }
    }

    public double getRevenueByMonth(int year, int month) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(totalMoney) FROM Order WHERE YEAR(createdAt) = :year AND MONTH(createdAt) = :month AND status != 'CANCELLED'",
                    Double.class
            );
            query.setParameter("year", year);
            query.setParameter("month", month);
            Double result = query.uniqueResult();
            return result != null ? result : 0.0;
        }
    }

    public double getRevenueByYear(int year) {
        try (Session session = sessionFactory.openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(totalMoney) FROM Order WHERE YEAR(createdAt) = :year AND status != 'CANCELLED'",
                    Double.class
            );
            query.setParameter("year", year);
            Double result = query.uniqueResult();
            return result != null ? result : 0.0;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}