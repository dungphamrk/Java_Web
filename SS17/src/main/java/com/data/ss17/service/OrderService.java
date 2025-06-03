package com.data.ss17.service;

import com.data.ss17.model.Order;
import com.data.ss17.model.OrderDetail;
import com.data.ss17.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
