package com.struts2.day03.service;

import com.struts2.day03.dao.CustomerDao;
import com.struts2.day03.domain.Customer;
import com.struts2.day03.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerService {

    public void saveCustomer(Customer customer) {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            new CustomerDao().saveCustomer(customer);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<Customer> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        List<Customer> customerList = null;
        try {
            customerList =  new CustomerDao().findAll();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return customerList;
    }
}
