package com.struts2.day01.action;
/**
 * Stuts2框架都使用Action类处理用户的请求
 * @author Administrator
 */
public class HelloAction {
    /**
     * Action类中的方法签名有要求的，必须这么做
     * public 共有的
     * 必须有返回值，必须String类型
     * 方法名称可以是任意的，但是不能有参数列表
     * 页面的跳转：
     * 	1. return "字符串"
     * 	2. 需要在strtus.xml配置文件中，配置跳转的页面
     */
    public String sayHello(){
        // 编写代码 接收请求的参数
        System.out.println("Hello Struts2!!");
        return "ok";
    }

    /**
     * 演示的method方法的默认值
     * @return
     */
    public String execute(){
        System.out.println("method方法的默认值是execute");
        return null;
    }

}
