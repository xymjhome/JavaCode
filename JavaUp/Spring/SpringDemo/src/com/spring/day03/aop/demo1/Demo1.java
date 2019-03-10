package com.spring.day03.aop.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1 {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;


    @Test
    public void run(){
        customerDao.save();
        customerDao.update();
    }
}
