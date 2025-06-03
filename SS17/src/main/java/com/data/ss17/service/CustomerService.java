package com.data.ss17.service;

import com.data.ss17.model.Customer;
import com.data.ss17.repository.CustomerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public Customer getCustomerByUsername(String username) {
        try (var session = sessionFactory.openSession()) {
            Query<Customer> query = session.createQuery("FROM Customer WHERE username = :username", Customer.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        }
    }
}