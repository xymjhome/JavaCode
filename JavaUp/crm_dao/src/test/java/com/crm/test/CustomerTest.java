package com.crm.test;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CustomerTest {

    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        CustomerDao customerDao = (CustomerDao) ac.getBean("customerDao");

        List<Customer> list = customerDao.findAll();
        System.out.println(list.size());
    }
}
