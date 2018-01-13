package com.servlet.life;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet生命周期 ★★★
 void init(ServletConfig config):初始化

 void service(ServletRequest request,ServletResponse response):服务 处理业务逻辑

 void destroy():销毁


 serlvet是单实例多线程
 默认第一次访问的时候,服务器创建servlet,并调用init实现初始化操作.并调用一次service方法
 每当请求来的时候,服务器创建一个线程,调用service方法执行自己的业务逻辑
 当serlvet被移除的时候服务器正常关闭的时候,服务器调用servlet的destroy方法实现销毁操作.
 */
public class LifeServlet implements Servlet{

    /* 初始化方法
     * 执行者:服务器
     * 执行次数:一次
     * 执行时机:默认第一次访问的时候*/
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init() 555555555555");
    }
    /* 服务
     * 执行者:服务器
     * 执行次数:请求一次执行一次
     * 执行时机:请求来的时候*/
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service() 666666666666666");
    }

    /* 销毁
     * 执行者:服务器
     * 执行次数:只执行一次
     * 执行时机:当servlet被移除的时候或者服务器正常关闭的时候*/
    @Override
    public void destroy() {
        System.out.println("destory() 4444444444444");
    }
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }



    @Override
    public String getServletInfo() {
        return null;
    }


}
