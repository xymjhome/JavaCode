package com.crm.dao;

import com.crm.domain.Customer;

import java.util.List;


public interface CustomerDao {

	/**
	 * 查询出Customer 表中的所有记录
	 * @return
	 */
	public List<Customer> findAll();
}
