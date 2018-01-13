package com.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("start Servlet doGet");
        String name = req.getParameter("username");

        resp.setContentType("text/html;charset=utf-8");

        System.out.println(name);
        resp.getWriter().println("data is :" + name+"</br>");
        resp.getWriter().println("数据哈哈是:" + name);
    }
}
