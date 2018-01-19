package com.ajaxDemo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxDemo.service.KeyWordService;

/**
 * 模仿百度 模糊
 */
@WebServlet(name = "SearchKwServlet",urlPatterns = "/searchKw")
public class SearchKwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//1.获取kw
		String kw=request.getParameter("kw");
		
		//2.调用service完成模糊操作 返回值:list
		List<Object> list=null;
		try {
			list = new KeyWordService().findKw4Ajax(kw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3.将数据  [a,aa,aaa] 去掉括号 写回去  a,aaa,aa
		if(list!=null && list.size()>0){
			String s = list.toString();
			s=s.substring(1, s.length()-1);
			System.out.println(s);
			response.getWriter().println(s);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
