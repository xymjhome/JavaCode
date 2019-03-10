package com.store.servlet;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminProductServlet",urlPatterns = "/adminProduct")
public class AdminProductServlet extends BaseServlet {

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService productService = (ProductService) BeanFactory.getBean("ProductService");
        try {
            List<Product> list = productService.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        重定向/store/adminCategory?method=findAll
        return "/admin/product/list.jsp";
    }


    public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryServiceImpl();
        try {
            List<Category> all = categoryService.findAll();
            request.setAttribute("clist",all);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/admin/product/add.jsp";
    }
}
