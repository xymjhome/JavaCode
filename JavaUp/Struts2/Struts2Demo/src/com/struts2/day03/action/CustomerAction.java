package com.struts2.day03.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.struts2.day03.domain.Customer;
import com.struts2.day03.domain.User;
import com.struts2.day03.service.CustomerService;
import org.apache.struts2.ServletActionContext;

import java.util.List;

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

    /**
     * 查询所有的客户
     * @return
     */
    public String list(){
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        System.out.println(existUser);
        System.out.println("enter list function");
        List<Customer> clist = new CustomerService().findAll();
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.set("clist",clist);
        return "list";
    }

}
