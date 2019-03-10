package com.store.servlet;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;
import com.store.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/adminCategory")
public class AdminCategoryServlet extends BaseServlet {

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
            List<Category> categories = categoryService.findAll();
            System.out.println("clist:" + categories.size());
            request.setAttribute("clist", categories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/admin/category/list.jsp";
    }


    public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        return "/admin/category/add.jsp";
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCid(UUIDUtils.getId());
        category.setCname(cname);

        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
            categoryService.add(category);
            response.sendRedirect(request.getContextPath() + "/adminCategory?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取cid
        String cid = request.getParameter("cid");
//        调用service 通过id获取一个分类,
        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
            Category category = categoryService.getById(cid);
            //                将category放入request域中
            request.setAttribute("bean",category);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        请求转发 edit.jsp
        return "/admin/category/edit.jsp";
    }


    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        获取cid和cname
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
//                封装成category
        Category category = new Category();
        category.setCname(cname);
        category.setCid(cid);
//        调用service的update方法
        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
           categoryService.update(category);
           response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        重定向/store/adminCategory?method=findAll
        return null;
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        获取cid和cname
        String cid = request.getParameter("cid");

//        调用service的delete法
        CategoryService categoryService = (CategoryService) BeanFactory.getBean("CategoryService");
        try {
           categoryService.delete(cid);
            response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        重定向/store/adminCategory?method=findAll
        return null;
    }
}
