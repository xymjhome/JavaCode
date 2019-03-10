package com.store.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet",urlPatterns = "/baseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //1、获取子类
            Class clazz = this.getClass();
            System.out.println("self class :"+clazz);
            //2、获取request中的封装的方法参数
            String methodName = request.getParameter("method");
            System.out.println("methodName :"+ methodName);
            if (methodName == null) {
                methodName = "index";
            }
            //3、获取方法对象
            System.out.println("after if methodName :"+ methodName);
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            String invoke = (String) method.invoke(this, request,response);
            System.out.println("请求相应的子servlet的返回值,用于重定向" + invoke);
            if (invoke != null) {
                request.getRequestDispatcher(invoke).forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    protected String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

}
