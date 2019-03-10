package com.crm.service.impl;

import com.crm.domain.Customer;
import com.crm.dao.CustomerDao;
import com.crm.service.CustomerService;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {
	//注值
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
