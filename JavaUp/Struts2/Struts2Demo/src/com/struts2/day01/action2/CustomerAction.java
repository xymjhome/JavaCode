package com.struts2.day01.action2;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 编写的客户的Action的类
 * @author Administrator
 */
public class CustomerAction extends ActionSupport {

    public String save(){
        System.out.println("保存客户成功。。。。。。。。。。。。");
        return NONE;
    }

    public String delete(){
        System.out.println("删除客户成功。。。。。。。。。。。。");
        return NONE;
    }
}
