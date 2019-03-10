package com.listener;

import javax.servlet.http.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class Person implements HttpSessionActivationListener,Serializable {
    private int id;
    private String name;

    public Person() {
    }



    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("person 对象被钝化，即直接写入磁盘");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("person 对象被活化，即从磁盘加载到内存");
    }
}
