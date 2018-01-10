package com.reflect.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
    private  Object obj;
    MyInvocationHandler(Object obj){
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        Class clazz = proxy.getClass();
        System.out.println("run InvocationHandler func start");
        method.invoke(obj,args);
        System.out.println("run InvocationHandler func end");
        return null;
    }
}
