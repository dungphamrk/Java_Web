package com.data.ss14.service.B8;

import com.data.ss14.repository.B8.OrderDAO;
import com.data.ss14.model.B8.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void processOrder(Order order) {
        orderDAO.saveOrder(order);
    }
}

