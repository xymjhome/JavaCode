<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/8/5 0005
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Demo</title>
  </head>
  <body>
  <a href="/struts2/day03/demo1.jsp">day03_demo1</a><br>
  <hr>

  <a href=${pageContext.request.contextPath}/valueStack_execute.action>valueStack_execute_demo1</a><br>

  <hr>

  <a href=${pageContext.request.contextPath}/valueStack_save.action>valueStack_save_demo1</a><br>
  </body>
</html>
