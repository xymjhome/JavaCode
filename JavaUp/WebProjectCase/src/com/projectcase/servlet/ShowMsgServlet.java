package com.projectcase.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowMsgServlet",urlPatterns = "/showmsg")
public class ShowMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ShowMsgServlet doPost");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ShowMsgServlet doGet");
        response.setContentType("text/html;charset=utf-8");

        response.getWriter().print(request.getAttribute("msg"));
    }
}
