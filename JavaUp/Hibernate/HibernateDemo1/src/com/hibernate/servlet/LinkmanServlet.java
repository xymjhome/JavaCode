package com.hibernate.servlet;

import com.hibernate.demo.day3.domain.Linkman;
import com.hibernate.service.LinkmanService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class LinkmanServlet extends BaseServlet {

    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lkmName = request.getParameter("lkmName");

        LinkmanService linkmanService = new LinkmanService();
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        if (StringUtils.isNotEmpty(lkmName)) {
            criteria.add(Restrictions.like("lkmName","%" + lkmName + "%"));
        }
        List<Linkman> linkmanList = linkmanService.findAll(criteria);
        request.setAttribute("list",linkmanList);
        return "jsp/linkman/list.jsp";
    }
}
