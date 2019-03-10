package com.store.servlet;

import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet",urlPatterns = "/adminOrder")
public class AdminOrderServlet extends BaseServlet {
    /**
     *	查询订单
     * @throws Exception
     */
    public  String findAllByState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.接受state

        String state=request.getParameter("state");
        System.out.println("state:" + state);

        //2.调用service
        OrderService os=(OrderService) BeanFactory.getBean("OrderService");
        List<Order> list=os.findAllByState(state);

        //3.将list放入域中 请求转发
        System.out.println(list);
        request.setAttribute("list", list);
        return "/admin/order/list.jsp";
    }


    public  String getDetailByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String oid = request.getParameter("oid");
        OrderService os=(OrderService) BeanFactory.getBean("OrderService");
        List<OrderItem> list=os.getById(oid).getItems();

        JsonConfig jsonConfig = JsonUtil.configJson(new String[]{"class", "order", "itemid"});
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        System.out.println(jsonArray);
        response.getWriter().println(jsonArray);

        return null;
    }

    /*
	 * 修改订单状态
	 */
    public  String updateState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.接受 oid state
        String oid = request.getParameter("oid");
        String state = request.getParameter("state");

        //2.调用service
        OrderService os=(OrderService) BeanFactory.getBean("OrderService");
        Order order = os.getById(oid);
        order.setState(2);

        os.update(order);

        //3.页面重定向
        response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
        return null;
    }
}
