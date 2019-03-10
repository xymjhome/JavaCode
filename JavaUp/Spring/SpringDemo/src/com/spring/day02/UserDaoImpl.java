package com.spring.day02;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * UserDaoImpl交给IOC的容器
 * @author Administrator
 */

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao{
    @Override
    public void save() {
        System.out.println("保存客户...");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化...");
    }
}
