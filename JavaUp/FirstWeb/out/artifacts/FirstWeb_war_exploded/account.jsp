<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/17 0017
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trans Account</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/account">
        <table>
            <tr>
                <td>转入方：</td>
                <td><input type="text" name="touser"></td>
            </tr>
            <tr>
                <td>转出方：</td>
                <td><input type="text" name="fromuser"></td>
            </tr>
            <tr>
                <td>金额：</td>
                <td><input type="text" name="money"></td>
            </tr>
            <tr>
                <td colspan = "2"><input type="submit" value = "转账"></td>

            </tr>
        </table>
    </form>
</body>
</html>
