package com.reflect.dynamicproxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        System.out.println("use danamic proxy pro");
        StudentImpl student = new StudentImpl();
        student.login();
        student.reset();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

//        public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
        System.out.println("use danamic proxy after");
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(student);
        Student s = (Student) Proxy.newProxyInstance(student.getClass().getClassLoader(),student.getClass().getInterfaces(),myInvocationHandler);
        s.login();
        s.reset();


        UserImpl user = new UserImpl();
        user.add();
        user.delete();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        MyInvocationHandler myInvocationHandler1 = new MyInvocationHandler(user);
        User user1 = (User)Proxy.newProxyInstance(user.getClass().getClassLoader(),user.getClass().getInterfaces(),myInvocationHandler1);
        user1.add();
        user1.delete();

    }
}
