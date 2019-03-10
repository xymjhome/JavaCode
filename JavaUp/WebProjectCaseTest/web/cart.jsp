<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/15 0015
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
    <table border="1" align="center" width="20%">
        <tr>
            <td>商品名称</td> <td>商品数量</td>
        </tr>

        <%
            //String name = request.get
           // Map<String,Integer>
            Map<String,Integer> map =  (Map<String,Integer>)session.getAttribute("cart");

            if (map == null)
            {
                out.print("<tr><td colspan=2>亲，您还没有往购物车中添加东西</td></tr>");
            }else{
                for (String name : map.keySet())
                {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(name);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(map.get(name));
                    out.print("</td>");
                    out.print("</tr>");
                }
            }
        %>
    </table>
    <hr>
    <center><a href="/WebProjectCaseTest/product_list.jsp">继续购物</a></center>
    <center><a href="/WebProjectCaseTest/clearCart">清空购物车</a></center>
</body>
</html>
