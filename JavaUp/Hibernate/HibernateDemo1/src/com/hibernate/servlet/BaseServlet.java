package com.hibernate.servlet;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        if (StringUtils.isEmpty(methodName)) {
            methodName = "index";
        }

        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String invoke = (String) method.invoke(this, request, response);
            if (StringUtils.isNotEmpty(invoke)) {
                request.getRequestDispatcher(invoke).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       return null;
    }
}
