package com.struts2.day03.dao;

import com.struts2.day03.domain.Customer;
import com.struts2.day03.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class CustomerDao {

    public void saveCustomer(Customer customer) {
        Session session = HibernateUtils.getCurrentSession();
        session.save(customer);
    }

    public List<Customer> findAll() {

        Session session = HibernateUtils.getCurrentSession();
        return session.createQuery("from Customer ").list();
    }
}
