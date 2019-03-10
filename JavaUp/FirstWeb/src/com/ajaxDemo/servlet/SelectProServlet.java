package com.ajaxDemo.servlet;

import com.ajaxDemo.domain.Province;
import com.ajaxDemo.service.ProvinceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
@WebServlet(name = "SelectProServlet",urlPatterns = "/selectPro")
public class SelectProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            List<Province> list = new ProvinceService().findAll();

            if (list != null && list.size() > 0){
                response.getWriter().print(JSONArray.fromObject(list));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
