package com.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * ContextServlet:理解
 上下文(全局管理者)
 一个项目的引用.代表了当前项目.
 当项目启动的时候,服务器为每一个web项目创建一个servletcontext对象.
 当项目被移除的时候或者服务器关闭的时候servletcontext销毁
 作用:
 1.获取全局的初始化参数
 2.共享资源(xxxAttribute)
 3.获取文件资源
 4.其他操作
 获取servletcontext:
 方式1:了解
 getServletConfig().getServletContext()
 方式2:
 getServletContext()
 常用方法:
     1.了解
     String  getInitParameter(String key):通过名称获取指定的参数值
     Enumeration getInitParameterNames() :获取所有的参数名称
     在根标签下有一个 context-param子标签 用来存放初始化参数
     <context-param>
     <param-name>encoding</param-name>
     <param-value>utf-8</param-value>
     </context-param>
     2.xxxAttribute
     3.
     String getRealPath(String path):获取文件部署到tomcat上的真实路径(带tomcat路径)
     getRealPath("/"):D:\javaTools\apache-tomcat-7.0.52\webapps\day09\
     InputStream getResourceAsStream(String path):以流的形式返回一个文件
     4.获取文件的mime类型  大类型/小类型
     String getMimeType(String 文件名称)
 */
public class ContextServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String cod = servletContext.getInitParameter("encoding");
        System.out.println(cod);

        String path = servletContext.getRealPath("/");
        System.out.println(path);

        InputStream resourceAsStream = servletContext.getResourceAsStream("/1.txt");
        System.out.println(resourceAsStream);


        String type = servletContext.getMimeType("1.txt");
        System.out.println(type);
    }
}
