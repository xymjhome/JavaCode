package com.servlet.servletconfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * servlet配置对象
 作用:
 1.获取当前servlet的名称
 2.获取当前servlet的初始化参数
 3.获取全局管理者
 方法:
 String getServletName():获取当前servlet的名称(web.xml配置的servlet-name)

 String  getInitParameter(String key):通过名称获取指定的参数值
 Enumeration getInitParameterNames() :获取所有的参数名称
 初始化参数是放在 web.xml文件
 servlet标签下子标签 init-param

 ★getServletContext():获取全局管理者
 servletconfig是由服务器创建的,在创建servlet的同时也创建了它,通过servlet的init(ServletConfig config)将config对象
 传递给servlet,由servlet的getServletConfig方法获取
 */
public class ConfigServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletConfig servletConfig = this.getServletConfig();
        String servletName = servletConfig.getServletName();
        System.out.println("servletName is :"+servletName);
        String user = servletConfig.getInitParameter("user");
        System.out.println("获取单一参数："+user);

        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements())//注意此处遍历Enumeration的方式
        {
            String name = initParameterNames.nextElement();
            System.out.println(name);
        }

    }
}
