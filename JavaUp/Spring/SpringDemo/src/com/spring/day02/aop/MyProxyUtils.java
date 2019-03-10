package com.spring.day02.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用JDK的方式生成代理对象
 *
 * @author Administrator
 */
public class MyProxyUtils {
    // 使用Proxy类生成代理对象
    public static UserDao getProxy(UserDao userDao){
        UserDao dao = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            // 代理对象方法一直线，invoke方法就会执行一次
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("打印日子、。。。。");
                }

                // 提交事务
                // 让dao类的save或者update方法正常的执行下去
                return method.invoke(userDao, args);
            }
        });
        return dao;
    }
}
