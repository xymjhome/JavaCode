package com.hibernate.service;

import com.hibernate.dao.CustomerDao;
import com.hibernate.domain.Customer;

import java.util.List;

public class CustomerService {

    public void add(Customer c) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.add(c);
    }

    public  List<Customer> findAll() {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.findAll();
    }

    public Customer getByCustId(String custId) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getByCustId(custId);
    }

    public void edit(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.edit(customer);
    }

    public void delete(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.delete(customer);
    }

    public List<Customer> findAll(String custName) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.findAll(custName);
    }
}
