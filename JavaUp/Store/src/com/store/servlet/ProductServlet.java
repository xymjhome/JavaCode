package com.store.servlet;

import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductServlet",urlPatterns = "/product")
public class ProductServlet extends BaseServlet {
    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        System.out.println(pid);
        ProductService productService = new ProductServiceImpl();
        try {
            Product product = productService.getProductById(pid);
            request.setAttribute("pBean",product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "/jsp/product_info.jsp";
    }

    public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        Integer currPage = Integer.parseInt(request.getParameter("currPage"));
        Integer pageSize = 12;

        ProductService productService = new ProductServiceImpl();
        PageBean<Product> pageBean = null;
        try {
            pageBean = productService.findByPage(currPage,pageSize,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pb",pageBean);

        return "/jsp/product_list.jsp";
    }

}
