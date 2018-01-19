package com.ajaxDemo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxDemo.domain.User;
import com.ajaxDemo.service.UserServcie;

/**
 * 原生ajax检测用户名是否被占用checkUsername4Ajax
 */
@WebServlet(name = "CheckUsername4AjaxServlet",urlPatterns = "/checkUsername4Ajax")
public class CheckUsername4AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.设置编码
		
		//1.接受用户名
		String username=request.getParameter("username");
		username=new String(username.getBytes("iso-8859-1"),"utf-8");
		System.out.println(username);
		
		//2.调用service 完成查询 返回值 user
		User user=null;
		try {
			user = new UserServcie().checkUsername4Ajax(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3.判断user 写回信息
		if(user == null){
			response.getWriter().println("1");
		}else{
			response.getWriter().println("0");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
