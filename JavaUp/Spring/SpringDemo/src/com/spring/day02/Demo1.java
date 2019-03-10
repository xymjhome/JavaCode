package com.spring.day02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1 {
    /**
     * 原来的方式
     */
    @Test
    public void run1(){
        UserService us = new UserServiceImpl();
        us.sayHell();
    }

    @Test
    public void run2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.sayHell();
    }

}
