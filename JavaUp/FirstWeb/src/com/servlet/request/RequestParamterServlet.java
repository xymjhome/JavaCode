package com.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**
操作请求参数 ★
        username=tom&password=123&hobby=drink&hobby=sleep
        常用方法:
        String getParameter(String key):获取一个值
        String[] getParameterValues(String key):通过一个key获取多个值
        Map<String,String[]> getParameterMap():获取所有的参数名称和值
        */
@WebServlet(name = "RequestParamterServlet",urlPatterns = "/param")
public class RequestParamterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("username:"+username);

        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("hobby"+ Arrays.toString(hobbies));

        System.out.println("========================================");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()
             ) {
            System.out.println(key +":"+Arrays.toString(parameterMap.get(key)));
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
