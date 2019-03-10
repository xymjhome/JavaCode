package com.crm.service;

import com.crm.domain.Customer;

import java.util.List;


public interface CustomerService {
/**
 * 查询所有客户列表
 * @return
 */
	public List<Customer> findAll();
}
