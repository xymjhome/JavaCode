package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter(filterName = "EncodingByDynamicProxyFilter",urlPatterns = "/*")
public class EncodingByDynamicProxyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpServletRequest requsetProxy = (HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("getParameter".equalsIgnoreCase(method.getName())) {
                    String func = request.getMethod();
                    if ("get".equalsIgnoreCase(func)) {
                        String invoke = (String) method.invoke(request, args);
                        return new String(invoke.getBytes("iso-8859-1"), "utf-8");
                    } else if ("post".equalsIgnoreCase(func)) {
                        request.setCharacterEncoding("utf-8");
                        return method.invoke(request, args);
                    }
                }
                return method.invoke(request, args);
            }
        });

        chain.doFilter(requsetProxy, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
