package com.data.ss17.repository;

import com.data.ss17.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Customer getCustomerById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, id);
        }
    }

    public void updateCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
        }
    }
}