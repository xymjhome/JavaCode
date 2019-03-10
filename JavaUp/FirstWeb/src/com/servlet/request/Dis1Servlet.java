package com.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Dis1Servlet",urlPatterns = "/dis1")
public class Dis1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求第一次到Dis1");
        System.out.println(request.getParameter("money"));
        //request.setAttribute("username", "++");
        request.setAttribute("username","wangwy");
        //请求转发到Dis2
        request.getRequestDispatcher("/dis2").forward(request,response);

    }
}
