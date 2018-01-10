package com.reflect.dynamicproxy;

public class StudentImpl implements Student {
    @Override
    public void login() {
        System.out.println("登陆");
    }

    @Override
    public void reset() {
        System.out.println("注销");
    }
}
