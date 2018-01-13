package com.servlet.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Location1Servlet",urlPatterns = "/loc1")
public class Location1Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //重定向
        //1、设置状态码（302），设置重定向头属性location
//        System.out.println("loc1 开始重定向到loc2");
//        response.setStatus(302);
//        response.setHeader("location","/FirstWeb/loc2");


        //2、直接使用response方法
        System.out.println("loc1 使用  response.sendRedirect() 开始重定向到loc2");
        response.sendRedirect("/FirstWeb/loc2");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
