package com.store.servlet;

import com.store.constant.Constant;
import com.store.domain.User;
import com.store.myconventer.MyConventer;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.MD5Utils;
import com.store.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("into UserServlet register Function!!!");
        User user = new User();
        try {
            ConvertUtils.register(new MyConventer(), Date.class);
            BeanUtils.populate(user, request.getParameterMap());

            user.setUid(UUIDUtils.getId());
            user.setCode(UUIDUtils.getCode());
            user.setPassword(MD5Utils.md5(user.getPassword()));
            UserService userService = new UserServiceImpl();
            userService.register(user);

            request.setAttribute("msg", "用户已经注册成功，请去邮箱激活！！！");

            return "/jsp/msg.jsp";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserService userService = new UserServiceImpl();
        User user = null;
        try {
            user = userService.active(code);
            if (user == null) {
                request.setAttribute("msg", "没找到此用户，请重新注册！！");
            } else {
                request.setAttribute("msg", "激活成功！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "/jsp/msg.jsp";
    }


    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/login.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        pwd = MD5Utils.md5(pwd);

        UserService userService = new UserServiceImpl();
        User user = null;
        try {
            user = userService.login(username, pwd);
            if (user == null) {
                request.setAttribute("msg", "登陆的用户不存在@@@！！！！");
                return "/jsp/msg.jsp";
            } else {
                if (Constant.USER_IS_ACTIVE != user.getState()) {
                    request.setAttribute("msg", "用户未激活");
                    return "/jsp/msg.jsp";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //重定向，相当于一次新的请求，全局保存的东西要放到session中
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/");
        return null;

        //请求转发，是基于上一次连接的页面，再转发到下一个页面，其URL是请求转发钱页面的URL
        //request.setAttribute("user", user);
        //return "/jsp/index.jsp";
    }


    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //干掉session
        request.getSession().invalidate();

        //重定向
        response.sendRedirect(request.getContextPath());

        //处理自动登录
        return null;
    }
}
