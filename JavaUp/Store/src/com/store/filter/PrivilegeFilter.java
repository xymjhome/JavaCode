package com.store.filter;

import com.store.domain.User;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "PrivilegeFilter",urlPatterns = {"/jsp/cart.jsp","/jsp/order_info.jsp","/jsp/order_list.jsp"},servletNames = "OrderServlet")
public class PrivilegeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//1.强转
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		//2.业务逻辑
		//从session中获取user 判断user是否为空 若为空 请求转发
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("msg", "没有权限,请先登录!");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			return;
		}
		
		//3.放行
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
