package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/21 0021.
 * 静态代理装饰者模式，实现对request的编码
 *
 * 静态代理书写步骤:
     1.要求被装饰者和装饰者实现同一个接口或者继承同一个类
     2.在装饰者中要有被装饰者的引用
     3.对需要加强的方法进行加强
     4.对不需要加强的方法调用原来的方法
 */
@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        chain.doFilter(new MyRequest(request), response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
class MyRequest extends HttpServletRequestWrapper{

    private HttpServletRequest request;
    private boolean flag = true;

    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        if (name == null  || name.trim().length() == 0){
            return null;
        }
        String[] str = getParameterValues(name);
        if (str == null || str.length == 0){
            return null;
        }
        return str[0];
    }

    @Override
    /**
     * hobby=[eat,drink]
     */
    public String[] getParameterValues(String name) {
        if (name == null || name.trim().length() == 0){
            return null;
        }
        Map<String, String[]> map = getParameterMap();
        if (map == null || map.size() == 0)
        {
            return null;
        }
        return map.get(name);
    }

    @Override
    /**
     * map{ username=[tom],password=[123],hobby=[eat,drink]}
     */
    public Map<String, String[]> getParameterMap() {

        /**
         * 首先判断请求方式
         * 若为post  request.setchar...(utf-8)
         * 若为get 将map中的值遍历编码就可以了
         */
        String method = request.getMethod();
        if ("post".equals(method)){
            try {
                request.setCharacterEncoding("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request.getParameterMap();
        }else if ("get".equals(method)){
            Map<String, String[]> map = super.getParameterMap();
            if (flag){
                for (String key : map.keySet()){
                    String[] arr = map.get(key);
                    for (int i = 0;i < arr.length;i++){
                        try {
                            arr[i] = new String(arr[i].getBytes("iso8859-1"),"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                flag = false;
            }
            return map;
        }
        return super.getParameterMap();
    }
}