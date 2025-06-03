package com.data.ss17.repository;

import com.data.ss17.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Customer> getCustomers(int page, int pageSize, String search) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Customer WHERE 1=1";
            if (search != null && !search.isEmpty()) {
                hql += " AND (LOWER(username) LIKE :search OR LOWER(email) LIKE :search)";
            }
            Query<Customer> query = session.createQuery(hql, Customer.class);
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search.toLowerCase() + "%");
            }
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getTotalCustomers(String search) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM Customer WHERE 1=1";
            if (search != null && !search.isEmpty()) {
                hql += " AND (LOWER(username) LIKE :search OR LOWER(email) LIKE :search)";
            }
            Query<Long> query = session.createQuery(hql, Long.class);
            if (search != null && !search.isEmpty()) {
                query.setParameter("search", "%" + search.toLowerCase() + "%");
            }
            return query.uniqueResult();
        }
    }

    public long getTotalCustomers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Customer", Long.class);
            return query.uniqueResult();
        }
    }
}