package com.xml.domain;

public class HelloMyServlet {

    public void add(){
        System.out.println("空参add方法");
    }
    public void add(int i,int j){
        System.out.println(i + j);
    }
    public void add(int i){
        System.out.println(i + 10);
    }
}
