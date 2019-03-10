package com.store.servlet;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends BaseServlet {
    @Override
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> newList = new ArrayList<>();
        List<Product> hotList = new ArrayList<>();

        ProductService productService = new ProductServiceImpl();
        try {
            newList = productService.findNew();
            hotList = productService.findHot();
            request.setAttribute("nList",newList);
            request.setAttribute("hList",hotList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "/jsp/index.jsp";
    }
}
