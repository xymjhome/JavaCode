package com.productcurd.servlet;

import com.productcurd.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DelCheckProductServlet",urlPatterns = "/delCheckProduct")
public class DelCheckProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] pids = request.getParameterValues("pid");

        try {
            new ProductService().deleteCheckedProduct(pids);

            response.sendRedirect(request.getContextPath()+"/findAll");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg","商品选中删除失败");
            request.getRequestDispatcher("/msg.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
