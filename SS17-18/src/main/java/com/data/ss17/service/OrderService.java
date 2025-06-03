package com.data.ss17.service;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import com.data.ss17.repository.OrderRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order, List<OrderDetail> orderDetails) {
        orderRepository.saveOrder(order, orderDetails);
    }

    public List<Order> getOrdersByCustomerId(Long customerId, int page, int pageSize) {
        return orderRepository.getOrdersByCustomerId(customerId, page, pageSize);
    }

    public long getTotalOrdersByCustomerId(Long customerId) {
        return orderRepository.getTotalOrdersByCustomerId(customerId);
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderRepository.getOrderDetailsByOrderId(orderId);
    }

    public List<Order> getOrders(int page, int pageSize, String recipientName, LocalDate startDate, LocalDate endDate, String status) {
        return orderRepository.getOrders(page, pageSize, recipientName, startDate, endDate, status);
    }

    public long getTotalOrders(String recipientName, LocalDate startDate, LocalDate endDate, String status) {
        return orderRepository.getTotalOrders(recipientName, startDate, endDate, status);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public void confirmOrder(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order != null) {
            order.setStatus("CONFIRMED");
            updateOrder(order);
        }
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        if (order != null) {
            order.setStatus("CANCELLED");
            updateOrder(order);
        }
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            updateOrder(order);
        }
    }

    public long getTotalOrders() {
        try (var session = orderRepository.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Order", Long.class);
            return query.uniqueResult();
        }
    }

    public double getRevenueByMonth(int year, int month) {
        try (var session = orderRepository.getSessionFactory().openSession()) {
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
        try (var session = orderRepository.getSessionFactory().openSession()) {
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
        return orderRepository.getSessionFactory();
    }
}