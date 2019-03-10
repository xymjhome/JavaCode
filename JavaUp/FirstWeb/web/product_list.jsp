<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/17 0017
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有商品展示</title>
</head>
<body>
    <table border="1" align="center">
        <tr>
            <td>id</td>
            <td>pname</td>
            <td>price</td>
            <td>pdesc</td>
        </tr>
        <c:forEach items="${plist}" var="pd">
            <tr>
                <td>${pd.id}</td>
                <td>${pd.pname}</td>
                <td>${pd.price}</td>
                <td>${pd.pdesc}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
