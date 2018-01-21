package com.filter.servlet;

import com.filter.domain.User;
import com.filter.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("进入LoginServlet的类内部doPost方法~~~~~~~~~~~~~~~~~~");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = new UserService().findUser(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null)
        {
            request.setAttribute("msg","用过名或者密码不匹配！");
            request.getRequestDispatcher("/filter/login.jsp").forward(request,response);
            System.out.println("结束LoginServlet的类内部doPost方法~~~~~~~~~~~~~~~~~~");
            return;
        }else{
            request.getSession().setAttribute("user",user);

            if ("ok".equals(request.getParameter("autoLogin"))){
                //注意名字可能为中文，cookie中不能存放中文，需要编码，然后前端显示用js解码
                Cookie c = new Cookie("autologin",URLEncoder.encode(username,"utf-8")+"-"+password);
                c.setMaxAge(3600);
                c.setPath(request.getContextPath()+"/");

                response.addCookie(c);
            }

            if ("ok".equals(request.getParameter("saveName"))){
                Cookie c = new Cookie("savename", URLEncoder.encode(username,"utf-8"));
                c.setMaxAge(3600);
                c.setPath(request.getContextPath()+"/");

                response.addCookie(c);
            }


            response.sendRedirect(request.getContextPath()+"/filter/success.jsp");
        }
        System.out.println("结束LoginServlet的类内部doPost方法~~~~~~~~~~~~~~~~~~");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
