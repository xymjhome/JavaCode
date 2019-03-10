package com.ssh.service;

import com.ssh.dao.CustomerDao;
import com.ssh.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void save(Customer customer) {
        System.out.println("业务层");
        System.out.println(customer);
        customerDao.save(customer);
    }
}
