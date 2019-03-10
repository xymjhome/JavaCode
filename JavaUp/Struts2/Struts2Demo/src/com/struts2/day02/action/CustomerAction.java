package com.struts2.day02.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.struts2.day02.domain.Customer;
import com.struts2.day02.service.CustomerService;

/**
 * 客户的控制器
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }


    /**
     * 保存客户
     * @return
     */
    public String save(){
        // 保存客户
        System.out.println(customer);
        new CustomerService().saveCustomer(customer);
        return NONE;
    }

}
