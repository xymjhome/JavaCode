package com.productcurd.servlet;

import com.productcurd.domain.Product;
import com.productcurd.service.ProductService;
import com.productcurd.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(name = "AddServlet",urlPatterns = "/addProduct")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //令牌机制，防止请求转发时造成重复添加商品的bug
        //String r_token = request.getParameter("r_token");
       // String s_token = (String) request.getSession().getAttribute("s_token");
        //第一次请求后就删除s_token
        //request.getSession().removeAttribute("s_token");

//        if (s_token == null || !s_token.equals(r_token)){
//            request.setAttribute("msg","请不要重复添加商品");
//            request.getRequestDispatcher("msg.jsp").forward(request,response);
//            return;
//        }


        Product product = new Product();
        try {
            System.out.println("开始添加商品");
            System.out.println(request.getParameter("pname"));
            System.out.println(request.getParameterMap());
            BeanUtils.populate(product,request.getParameterMap());

            //通过UUID工具类产生唯一的pid
            product.setPid(UUIDUtils.getPid());
            product.setPdate(new Date());

            new ProductService().addProduct(product);

            //请求转发方式到FindAllServlet
            //request.getRequestDispatcher("/findAll").forward(request,response);

            //避免重复添加，不使用令牌麻烦步骤可以使用重定向
            response.sendRedirect(request.getContextPath()+"/findAll");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","添加商品失败");
            request.getRequestDispatcher("msg.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
