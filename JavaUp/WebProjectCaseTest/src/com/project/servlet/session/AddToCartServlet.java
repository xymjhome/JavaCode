package com.project.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/15 0015.
 */
@WebServlet(name = "AddToCartServlet",urlPatterns = "/addToCart")
public class AddToCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String name = request.getParameter("name");
        name = new String(name.getBytes("iso8859-1"),"utf-8");
        Map<String,Integer> map = (Map<String,Integer>)request.getSession().getAttribute("cart");

        Integer count = 1;
        if (map == null){
            map = new HashMap<>();

            request.getSession().setAttribute("cart",map);
        }else{
            count = map.get(name);
            if (count == null)
            {
                count = 1;
            }else{
                ++count;
            }
        }
        map.put(name,count);

        writer.print("<b>已经将  "+name+"  商品添加到购物车</b><hr>");
        writer.print("<a href = '"+request.getContextPath()+"/product_list.jsp' >继续购物</a>&nbsp&nbsp&nbsp&nbsp");
        writer.print("<a href = '"+request.getContextPath()+"/cart.jsp' >查看购物车</a>&nbsp&nbsp&nbsp&nbsp");


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
