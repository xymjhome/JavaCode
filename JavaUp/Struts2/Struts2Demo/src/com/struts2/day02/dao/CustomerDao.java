package com.struts2.day02.dao;

import com.struts2.day02.domain.Customer;
import com.struts2.day02.utils.HibernateUtils;
import org.hibernate.Session;

public class CustomerDao {

    public void saveCustomer(Customer customer) {
        Session session = HibernateUtils.getCurrentSession();
        session.save(customer);
    }
}
