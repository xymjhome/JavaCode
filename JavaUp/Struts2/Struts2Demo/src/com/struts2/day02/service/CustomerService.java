package com.struts2.day02.service;

import com.struts2.day02.dao.CustomerDao;
import com.struts2.day02.domain.Customer;
import com.struts2.day02.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
