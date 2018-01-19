<%@ page import="com.productcurd.utils.UUIDUtils" %><%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/18 0018
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
    <%
        String token = UUIDUtils.getToken();
        session.setAttribute("s_token",token);

        pageContext.setAttribute("r_token",token);
    %>
    <form method="post" action="${pageContext.request.contextPath}/addProduct">
        <input type="hidden" name="r_token" value="${r_token}">
        <table border="1" align="center" width="20%">
            <tr>
                <td>商品名称</td>
                <td><input type="text" name="pname"></td>
            </tr>
            <tr>
                <td>市场价</td>
                <td><input type="text" name="market_price"></td>
            </tr>
            <tr>
                <td>商城价</td>
                <td><input type="text" name="shop_price"></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input type="text" name="pdesc"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="保存"></td>
            </tr>
        </table>
    </form>
</body>
</html>
