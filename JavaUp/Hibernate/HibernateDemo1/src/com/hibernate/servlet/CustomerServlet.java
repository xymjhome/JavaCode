package com.hibernate.servlet;

import com.hibernate.domain.Customer;
import com.hibernate.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CustomerServlet extends BaseServlet {
    public String addsubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String custName = request.getParameter("custName");
        System.out.println("resquest custname:" + custName);
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.get("custName")[0]);
        System.out.println(parameterMap.get("custLevel")[0]);
        System.out.println(parameterMap.get("custSource")[0]);
        System.out.println(parameterMap.get("custLinkman")[0]);
        Customer c = new Customer();
        try {
            BeanUtils.populate(c,parameterMap);
            CustomerService customerService = new CustomerService();
            customerService.add(c);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "jsp/success.jsp";
    }


    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custName = request.getParameter("custName");

        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.findAll(custName);
        System.out.println(customerList);
        request.setAttribute("list",customerList);
        return "jsp/customer/list.jsp";
    }


    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custId = request.getParameter("custId");
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getByCustId(custId);
        request.setAttribute("customer",customer);
        return "jsp/customer/edit.jsp";
    }

    public String editsubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custId = request.getParameter("custId");
        Long id = Long.parseLong(custId);
        Map<String, String[]> parameterMap = request.getParameterMap();
        Customer customer = new Customer();
        customer.setCustId(id);
        try {
            BeanUtils.populate(customer,parameterMap);
            CustomerService customerService = new CustomerService();
            customerService.edit(customer);
            return "jsp/success.jsp";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "jsp/error.jsp";

    }


    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String custId = request.getParameter("custId");
        Long id = Long.parseLong(custId);
        Customer customer = new Customer();
        customer.setCustId(id);
        CustomerService customerService = new CustomerService();
        customerService.delete(customer);
        return "jsp/success.jsp";
    }
}
