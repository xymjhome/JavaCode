package com.struts2.day01.action2;

/**
 * 动态方法访问方式
 * @author Administrator
 */
public class UserAction {

    public String save(){
        System.out.println("保存User成功。。。。。。。。。。。。");
        return "saveOk";
    }

    public String delete(){
        System.out.println("删除User成功。。。。。。。。。。。。");
        return "deleteOk";
    }
}
