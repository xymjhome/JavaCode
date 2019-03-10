package com.spring.day02.aop;

import org.junit.Test;

public class Demo1 {

    @Test
    public void run(){
        UserDao userDao = new UserDaoImpl();
        userDao.save();
        userDao.update();
        System.out.println("======================");
        UserDao proxy = MyProxyUtils.getProxy(new UserDaoImpl());
        proxy.save();
        proxy.update();
    }
}
