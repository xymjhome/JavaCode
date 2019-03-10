package com.store.utils;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    public static Object getBean(String id) {

        try {
            Document read = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
            Element ele = (Element)read.selectSingleNode("//bean[@id = '" + id + "']");
            String aClass = ele.attributeValue("class");

//            return Class.forName(aClass).newInstance();  //不使用代理，直接返回实体类对象
            final Object o = Class.forName(aClass).newInstance();
            if (id.endsWith("Service")) {
                Object proxyInstance = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("add".equals(method.getName()) || "register".equals(method.getName())) {
                            System.out.println("dynamic proxy for service add or register function");
                            return method.invoke(o, args);
                        }
                        return method.invoke(o, args);
                    }
                });
                return proxyInstance;
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getBean("CategoryService"));
    }
}
