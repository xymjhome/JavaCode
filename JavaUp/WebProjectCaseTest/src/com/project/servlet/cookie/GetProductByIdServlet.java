package com.project.servlet.cookie;

import com.project.servlet.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "GetProductByIdServlet",urlPatterns = "/getProductById")
public class GetProductByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        Cookie cookie = CookieUtils.getCookieByName("ids",request.getCookies());

        String ids = "";
        if (cookie == null){
            ids = id;
        }else
        {
            ids = cookie.getValue();
            String[] split = ids.split("-");
            List<String> strings = Arrays.asList(split);
            LinkedList<String> strings1 = new LinkedList<>(strings);
            if (strings1.contains(id)){
                strings1.remove(id);
                strings1.addFirst(id);
            }else{
                if (strings1.size()>2){
                    strings1.removeLast();
                    strings1.addFirst(id);
                }else{
                    strings1.addFirst(id);
                }
            }
            ids = "";
            for (String s : strings1){
                ids+=(s+ "-");
            }
            ids = ids.substring(0,ids.length()-1);
        }

        cookie = new Cookie("ids",ids);
        cookie.setPath(request.getContextPath()+"/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        //跳转到指定页面
        response.sendRedirect(request.getContextPath()+"/product_info"+id+".htm");
    }
}
