package com.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * request:请求
 作用:获取浏览器发送过来的数据
 组成部分:
 请求行 请求头 请求体
 操作请求行
 格式:
 请求方式 请求资源 协议/版本
 常用方法:HttpServletRequest
 掌握
 String getMethod():获取请求方式
 String getRemoteAddr():获取ip地址
 String getContextPath() :在java中获取项目名称  (/day10)


 了解:
 getRequestURI():获取的是 从项目名到参数之前的内容  /day10/regist
 getRequestURL():获取的带协议的完整路径   http://localhost/day10/regist
 String getQueryString():get请求的所有参数   username=tom&password=123
 String getProtocol():获取协议和版本


 操作请求头
 格式:key/value(value可以是多个值)
 常用方法:
 ★String getHeader(String key):通过key获取指定的value (一个)

 了解:
 Enumeration getHeaders(String name) :通过key获取指定的value(多个)
 Enumeration getHeaderNames() :获取所有的请求头的名称
 int getIntHeader(String key):获取整型的请求头
 long getDateHeader(String key):获取时间的请求头
 重要的请求头:
 user-agent:浏览器内核 msie firefox chrome
 referer:页面从那里来 防盗链
 */
@WebServlet(name = "RequestFuncServlet",urlPatterns = "/reqfunc")
public class RequestFuncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求方式
        String method = request.getMethod();
        System.out.println(method);

        //获取访问者IP地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);

        //获取项目名称
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        System.out.println("*********************************");
//        了解:
//        getRequestURI():获取的是 从项目名到参数之前的内容  /day10/regist
//        getRequestURL():获取的带协议的完整路径   http://localhost/day10/regist
//        String getQueryString():get请求的所有参数   username=tom&password=123
//        String getProtocol():获取协议和版本

        System.out.println(request.getRequestURI());

        System.out.println(request.getRequestURL());

        System.out.println(request.getQueryString());

        System.out.println(request.getProtocol());


        System.out.println("两个重要的请求头");
        //获取访问服务器的浏览器内核
        String header = request.getHeader("user-agent");
        System.out.println(header);

        //获取页面从那里来 防盗链
        String referer = request.getHeader("referer");
        if (referer == null)
        {
            System.out.println("没有访问");
        }else if(referer.contains("localhost"))
        {
            System.out.println("自己点");
        }
    }
}
