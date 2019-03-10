package com.store.servlet;

import com.store.domain.*;
import com.store.service.OrderService;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;
import com.store.utils.PaymentUtil;
import com.store.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;


@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends BaseServlet {


    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

//    1.先判断用户是否登录
        //User user = (User) request.getAttribute("user");不能直接从request域中取，user对象存储到session中，所以这样实现错误
        //0.判断用户是否登录
        User user=(User)request.getSession().getAttribute("user");//先获取request域中的session，再获取session中的user
        System.out.println("order user:" + user);
        if (user == null) {
            request.setAttribute("msg","请先登录再来提交订单！！！");
            return "/jsp/msg.jsp";
        }
//    2.封装order
//        private String oid;
//        private Date ordertime;
//        private double total;
//        private Integer state=0;//订单状态  0:未支付  1:已支付
//        private String address;
//        private String name;
//        private String telephone;
//        private User user;
//    oid user(session)  list对象(购物车) ordertime(当前时间) state(默认未支付:0)
        Order order = new Order();
        order.setOid(UUIDUtils.getId());
        Date date = new Date();          // 获取一个Date对象
        System.out.println(date);
        //Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println(sqlDate);
        order.setOrdertime(date);
        //Cart cart = (Cart) request.getAttribute("cart");错误！！！！！
        Cart cart = (Cart)request.getSession().getAttribute("cart");

        if (cart != null) {
            order.setTotal(cart.getTotalPrice());

            Collection<CartItem> items = cart.getItems();
            for(CartItem item : items) {
                OrderItem orderItem = new OrderItem();
//                private String itemid;
//                private int count;
//                private Product product;
//                private Order order;
                orderItem.setItemid(UUIDUtils.getId());
                orderItem.setCount(item.getCount());
                orderItem.setSubtotal(item.getSubtotal());
                orderItem.setProduct(item.getProduct());
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            }
        } else {
            request.setAttribute("msg","购物车为空！！！");
            return "/jsp/msg.jsp";
        }
        order.setUser(user);
//    3.调用orderservice 生成订单
        OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
        orderService.add(order);
//    4.清空购物车
        request.getSession().removeAttribute("cart");
//    5.将order放入request域中,请求转发 到Order_info.jsp
        request.setAttribute("bean",order);
        return "/jsp/order_info.jsp";
    }

    public String findAllByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        int currPage = Integer.parseInt(request.getParameter("currPage"));
        int pageSize = 3;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("msg","请先登录再来查看订单！！！");
            return "/jsp/msg.jsp";
        }

        OrderService orderService = (OrderService)BeanFactory.getBean("OrderService") ;
        //pageBean : list,currPage,pageSize,totalCount,totalPage;
        PageBean<Order> pageBean = null;
        try {
            pageBean = orderService.findAllByPage(currPage,pageSize,user);
        } catch (Exception e) {
            System.out.println("获取订单列表失败！！");
            e.printStackTrace();
        }

        request.setAttribute("pb",pageBean);
        return "/jsp/order_list.jsp";
    }

    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String oid = request.getParameter("oid");
        OrderService orderService = (OrderService) BeanFactory.getBean("OrderService");
        Order order = null;
        try {
            order = orderService.getById(oid);
        } catch (Exception e) {
            System.out.println("获取订单失败");
            e.printStackTrace();
        }

        request.setAttribute("bean",order);
        return "/jsp/order_info.jsp";
    }

    public String pay(HttpServletRequest request,HttpServletResponse respone) throws Exception{
        //接受参数
        String address=request.getParameter("address");
        String name=request.getParameter("name");
        String telephone=request.getParameter("telephone");
        String oid=request.getParameter("oid");


        //通过id获取order
        OrderService s=(OrderService) BeanFactory.getBean("OrderService");
        Order order = s.getById(oid);

        order.setAddress(address);
        order.setName(name);
        order.setTelephone(telephone);

        //更新order
        s.update(order);


        // 组织发送支付公司需要哪些数据
        String pd_FrpId = request.getParameter("pd_FrpId");
        String p0_Cmd = "Buy";
        String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
        String p2_Order = oid;
        String p3_Amt = "0.01";
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
        // 第三方支付可以访问网址
        String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);


        //发送给第三方
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        respone.sendRedirect(sb.toString());

        return null;
    }
    /**
     * 支付成功之后的回调
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        // 身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                "keyValue");

        // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
        boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);
        if (isValid) {
            // 响应数据有效
            if (r9_BType.equals("1")) {
                // 浏览器重定向
                System.out.println("111");
                request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");

            } else if (r9_BType.equals("2")) {
                // 服务器点对点 --- 支付公司通知你
                System.out.println("付款成功！222");
                // 修改订单状态 为已付款
                // 回复支付公司
                response.getWriter().print("success");
            }

            //修改订单状态
            OrderService s=(OrderService) BeanFactory.getBean("OrderService");
            Order order = s.getById(r6_Order);

            //修改订单状态为 已支付
            order.setState(1);

            s.update(order);

        } else {
            // 数据无效
            System.out.println("数据被篡改！");
        }


        return "/jsp/msg.jsp";

    }


    public String updateState(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String oid = request.getParameter("oid");
        OrderService orderService = (OrderService)BeanFactory.getBean("OrderService");
        Order byId = orderService.getById(oid);
        byId.setState(3);

        orderService.update(byId);
        response.sendRedirect(request.getContextPath() + "/order?method=findAllByPage&currPage=1");
        return null;
    }


    public static void main(String[] args) {
        java.sql.Timestamp now= new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(now);
        Date date = new Date();          // 获取一个Date对象
        System.out.println(date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println(sqlDate);
    }


}
