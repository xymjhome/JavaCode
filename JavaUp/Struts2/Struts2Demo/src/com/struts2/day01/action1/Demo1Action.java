package com.struts2.day01.action1;

/**
 * 就是POJO类：没有任何继承和实现
 * @author Administrator
 */
public class Demo1Action {
    /**
     * execute是默认方法
     * return null; 不会进行跳转
     * @return
     */
    public String execute(){
        System.out.println("Demo1Action就是POJO类...");
        return null;
    }
}
