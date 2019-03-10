package com.store.servlet;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.BeanFactory;
import com.store.utils.JsonUtil;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CategoryService categoryService = new CategoryServiceImpl();
        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
            List<Category> categoryList = categoryService.findAll();
            //request.setAttribute("categoryBeanList",categoryList);
            //String s1 = JsonUtil.list2json(categoryList);
            JSONArray jsonArray = JSONArray.fromObject(categoryList);
            String s = jsonArray.toString();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
