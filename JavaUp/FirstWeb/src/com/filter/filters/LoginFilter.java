package com.filter.filters;

import com.filter.domain.User;
import com.filter.service.UserService;
import com.filter.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入LoginFilter的doFilter方法");
        //1.强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //2.完成自动登录
        //2.1 判断session中有无登录登录用户 没有的话继续自动登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){

            //没有用户  需要自动登录
            //2.2 判断访问的资源是否和登录注册相关,若相关则不需要自动登录
            String path = request.getRequestURI();
            if (!path.contains("/login")){

                //2.3获取指定的cookie
                Cookie c = getCookieByName("autologin",request.getCookies());
                //判断cookie是否为空
                //若不为空 获取值(username和passowrd) 调用serivce完成登录  判断user是否为空 不为空 放入session
                if (c != null)
                {
                    String username = c.getValue().split("-")[0];
                    String password = c.getValue().split("-")[1];
                    try {
                        //调用serivce完成登录
                        user = new UserService().findUser(username,password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (user != null)
                    {
                        //将user放入session中
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }

        //3.放行
        chain.doFilter(req, resp);

        System.out.println("进入LoginFilter的doFilter方法执行结束");
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public static Cookie getCookieByName(String name, Cookie[] cookies){
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
