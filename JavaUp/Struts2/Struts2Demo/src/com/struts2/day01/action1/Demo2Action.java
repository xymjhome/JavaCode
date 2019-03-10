package com.struts2.day01.action1;

import com.opensymphony.xwork2.Action;

/**
 * 实现Action的接口，Action是框提供的接口
 * @author Administrator
 */
public class Demo2Action implements Action{
    @Override
    public String execute() throws Exception {
        System.out.println("Demo2Action实现了Action的接口");
        return LOGIN;
    }
}
