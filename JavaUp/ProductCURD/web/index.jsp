<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/18 0018
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>查询所有商品入口</title>
  </head>
  <body>
    <h1 align="center"><a href="${pageContext.request.contextPath}/findAll">查询所有商品</a></h1>
    <h1 align="center"><a href="${pageContext.request.contextPath}/add.jsp">添加商品</a></h1>
    <h1 align="center"><a href="${pageContext.request.contextPath}/getPageProduct?currPage=1">分页展示商品</a></h1>
  </body>
</html>
