package com.struts2.day01.action2;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 通配符的方式
 * @author Administrator
 */
public class LinkManAction extends ActionSupport{

    public String save(){
        System.out.println("保存联系人成功。。。。。。。。。。。。");
        return "saveOk";
    }

    public String delete(){
        System.out.println("删除联系人成功。。。。。。。。。。。。");
        return "deleteOk";
    }
}
