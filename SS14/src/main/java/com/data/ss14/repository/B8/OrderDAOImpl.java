package com.data.ss14.repository.B8;

import com.data.ss14.model.B8.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final List<Order> database = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        database.add(order);
    }
}

