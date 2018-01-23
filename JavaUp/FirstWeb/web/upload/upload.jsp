<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/22 0022
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="/FirstWeb/upload" method="post" enctype="multipart/form-data">
    用户名:<input name="username"><br/>
    文件:<input type="file" name="f"><br/>
    <input type="submit">
</form>
</body>
</html>
