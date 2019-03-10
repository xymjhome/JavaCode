package com.ajaxDemo.servlet;

import com.ajaxDemo.domain.City;
import com.ajaxDemo.service.CityService;
import net.sf.json.JSONArray;

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
@WebServlet(name = "SelectCityServlet",urlPatterns = "/selectCity")
public class SelectCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String pid = request.getParameter("pid");
        try {
            List<City> cityList = new CityService().findCitiesByPid(pid);
            if (cityList != null && cityList.size() > 0){
                response.getWriter().print(JSONArray.fromObject(cityList));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
