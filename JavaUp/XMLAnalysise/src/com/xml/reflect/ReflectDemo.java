package com.xml.reflect;

import com.xml.domain.HelloMyServlet;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import org.dom4j.Document;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectDemo {
    @Test
    public void reflectTest1() throws Exception {
        Class clazz = Class.forName("com.xml.domain.HelloMyServlet");

        HelloMyServlet instance = (HelloMyServlet) clazz.newInstance();

        instance.add();
        instance.add(10,30);
        instance.add(2);
    }

    @Test
    public void reflectTest2() throws Exception {
        Class clazz = Class.forName("com.xml.domain.HelloMyServlet");

        HelloMyServlet instance = (HelloMyServlet) clazz.newInstance();

        Method method = clazz.getMethod("add");
        method.invoke(instance);
    }

    @Test
    public void reflectTest3() throws Exception {
        Class clazz = HelloMyServlet.class;
        Constructor constructor = clazz.getConstructor();

        HelloMyServlet instance = (HelloMyServlet) constructor.newInstance();

        Method method = clazz.getMethod("add",int.class,int.class);
        method.invoke(instance,10,39);
    }
    @Test
    public void reflectTest4() throws Exception {
        Document document = new SAXReader().read("./xml/web.xml");
        Element ele = (Element) document.selectSingleNode("//servlet/servlet-class");
        System.out.println(ele.getText());

        Class clazz = Class.forName(ele.getText());
        HelloMyServlet instance = (HelloMyServlet) clazz.newInstance();

        Method method = clazz.getMethod("add",int.class,int.class);
        method.invoke(instance,15,39);

    }

    @Test
    public void reflectTest5() throws Exception {
        Document document = new SAXReader().read("./xml/web.xml");
        Element eleClass = (Element) document.selectSingleNode("//servlet-class");
        Element eleUrl = (Element) document.selectSingleNode("//url-pattern");

        Map<String,String> map = new HashMap<>();
        map.put(eleUrl.getText(),eleClass.getText());


        Class clazz = Class.forName(map.get("/hello"));
        HelloMyServlet instance = (HelloMyServlet) clazz.newInstance();

        Method method = clazz.getMethod("add",int.class,int.class);
        method.invoke(instance,15,39);

    }

}
