package com.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RequestChineseServlet",urlPatterns = "/paramchinese")
public class RequestChineseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第二种，直接设置request编码，但是值堆post请求有效
        request.setCharacterEncoding("utf-8");
        System.out.println("doGet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //第一种根据编码解码解决中文乱码
        //username = new String(username.getBytes("iso8859-1"),"utf-8");

        System.out.println(username+"::"+password);
    }
}
