package com.productcurd.servlet;

import com.productcurd.domain.Product;
import com.productcurd.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchProductServlet",urlPatterns = "/searchProduct")
public class SearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String kw = request.getParameter("kw");

        try {
            List<Product> products = new ProductService().searchProductByCondition(name, kw);

            request.setAttribute("plist",products);

            request.getRequestDispatcher("/product_list.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg","搜索商品失败");
            request.getRequestDispatcher("/msg.jsp").forward(request,response);
        }
    }
}
