package com.productcurd.servlet;

import com.productcurd.domain.PageBean;
import com.productcurd.domain.Product;
import com.productcurd.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetPageProductServlet",urlPatterns = "/getPageProduct")
public class GetPageProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int currPage = Integer.parseInt(request.getParameter("currPage"));
        int pageSize = 3;
        try {
            PageBean<Product> page = new ProductService().getPageBean(currPage,pageSize);
            request.setAttribute("page",page);

            request.getRequestDispatcher("/product_page_list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
