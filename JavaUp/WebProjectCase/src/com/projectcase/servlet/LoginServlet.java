package com.projectcase.servlet;

import com.projectcase.domain.User;
import com.projectcase.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 处理前端页面请求
 */
public class LoginServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {

        //ServletContext是服务器端所有Servlet的公共管理者，可以用于共享变量
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("count",0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String username = req.getParameter("username");
        String inputPassword = req.getParameter("password");
        System.out.println(username);
        System.out.println(inputPassword);
        User user = null;
        try {
            user = new UserService().login(username,inputPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null){
            resp.getWriter().println("登陆密码或者用户名不存在,3秒后跳转登陆页面");

            //定时刷新
            resp.setHeader("refresh","3;url=/WebProjectCase/login.htm");
        }else{
            resp.getWriter().println(user.getUsername()+":欢迎回来");

            ServletContext servletContext = getServletContext();
            //注意此处如果属性count没有在init初始化之要写Object count
            Integer count = (Integer)servletContext.getAttribute("count");

            servletContext.setAttribute("count",++count);
        }

    }
}
