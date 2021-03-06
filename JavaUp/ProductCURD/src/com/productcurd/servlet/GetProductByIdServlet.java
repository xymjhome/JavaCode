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

@WebServlet(name = "GetProductByIdServlet",urlPatterns = "/getProductById")
public class GetProductByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        try {
            Product p = new ProductService().getProductById(pid);

            request.setAttribute("bean",p);

            request.getRequestDispatcher("/edit.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg","删除前查询操作失败");
            request.getRequestDispatcher("/msg.jsp").forward(request,response);
        }
    }
}
