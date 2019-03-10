package com.crm.web;

import java.util.List;

import com.crm.domain.Customer;
import com.crm.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class CustomerAction extends ActionSupport {
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 查询所有数据
	 */
	public String execute() throws Exception {
		List<Customer> list = customerService.findAll();
		System.out.println(list.size());
		
		ActionContext.getContext().put("list", list);
		return SUCCESS;
	}
}
