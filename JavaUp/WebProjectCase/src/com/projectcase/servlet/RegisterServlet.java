package com.projectcase.servlet;

import com.projectcase.domain.User;
import com.projectcase.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            int row = new UserService().register(user);
            if (row == 1)
            {
                request.setAttribute("msg","用户注册成功");
                request.getRequestDispatcher("/showmsg").forward(request,response);
            }else {
                request.setAttribute("msg","用户注册失败");
                request.getRequestDispatcher("/showmsg").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
