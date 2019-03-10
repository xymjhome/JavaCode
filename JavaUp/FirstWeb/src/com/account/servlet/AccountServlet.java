package com.account.servlet;

import com.account.service.AccountService;
import com.account.service.AccountService4DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccountServlet",urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String touser = request.getParameter("touser");
        String fromuser = request.getParameter("fromuser");
        String money = request.getParameter("money");

        try {
            //使用ThreadLocal保证是同一个连接
            //new AccountService().account(fromuser,touser,money);

            //使用DBUtils，同时使用ThreadLocal绑定的连接
            new AccountService4DBUtils().account(fromuser,touser,money);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("转账失败！！");
            return;
        }

        response.getWriter().print("转账成功！！！");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
