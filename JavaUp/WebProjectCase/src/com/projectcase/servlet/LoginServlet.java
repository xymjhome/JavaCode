package com.projectcase.servlet;

import com.projectcase.domain.User;
import com.projectcase.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //response.getWriter().println("进去login servlet");

        String rCode = (String)request.getParameter("checkCode");
        String sCode = (String) request.getSession().getAttribute("sessionCode");

        //一次性验证码，用完后从session中移除
        request.getSession().removeAttribute("sessionCode");

        if (rCode == null || rCode.trim().length() == 0 || sCode == null){
            //验证码有问题，提示信息跳转到login.jsp
            request.setAttribute("msg","请输入验证码");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        if (!rCode.equalsIgnoreCase(sCode)){
            //验证码有问题，提示信息跳转到login.jsp
            request.setAttribute("msg","验证码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        String username = request.getParameter("username");
        String inputPassword = request.getParameter("password");
        System.out.println(username);
        System.out.println(inputPassword);
        User user = null;
        try {
            user = new UserService().login(username,inputPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null){
            /**
             * 测试定时刷新，以及成功登陆案例代码
             //response.getWriter().println("登陆密码或者用户名不存在,3秒后跳转登陆页面");
             //定时刷新
             //response.setHeader("refresh","3;url=/WebProjectCase/login.jsp");
             */
            request.setAttribute("msg","用户名和密码不匹配");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }else{
            /**
             * 测试定时刷新，以及成功登陆显示登陆次数案例代码
            response.getWriter().println(user.getUsername()+":欢迎回来");
            ServletContext servletContext = getServletContext();
            //注意此处如果属性count没有在init初始化之要写Object count
            Integer count = (Integer)servletContext.getAttribute("count");

            servletContext.setAttribute("count",++count);
             */

            //若user不为空，判断是否勾选了记住用户名
            if ("ok".equals(request.getParameter("savename"))){
                Cookie cookie = new Cookie("saveName", username);

                cookie.setPath(request.getContextPath()+"/");
                cookie.setMaxAge(3600);

                response.addCookie(cookie);
            }

            request.getSession().setAttribute("user",user);
        }

        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
