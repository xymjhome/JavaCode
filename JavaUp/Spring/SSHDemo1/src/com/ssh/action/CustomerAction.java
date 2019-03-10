package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 客户的控制层
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 保存客户的方法
     * @return
     */
    public String add(){
        System.out.println("WEB层：保存客户...");
		/*// WEB的工厂
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		CustomerService cs = (CustomerService) context.getBean("customerService");
		// 调用方法
		cs.save(customer);*/

        customerService.save(customer);

        return NONE;
    }


}
