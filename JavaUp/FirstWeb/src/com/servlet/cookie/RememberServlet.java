package com.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "RememberServlet",urlPatterns = "/remember")
public class RememberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        Cookie cookie = getCookieByName("lastTime", request.getCookies());
        if (cookie == null) {
            writer.print("你是第一次登陆");
        } else {
            String lastTime = cookie.getValue();
            long l = Long.parseLong(lastTime);
            Date date = new Date(l);
            writer.print("您上次登陆的时间是：" + date.toLocaleString());


        }
        cookie = new Cookie("lastTime", new Date().getTime() + "");
        //设置cookie的持久化，要设置持久化时间和路径
        cookie.setMaxAge(3600);
        cookie.setPath(request.getContextPath()+"/");

        response.addCookie(cookie);
    }

    private Cookie getCookieByName(String name, Cookie[] cookies) {
        if (cookies != null && name != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                if (name.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
