<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--include指令:--%>
<%--静态包含,就是将其他页面或者servlet的内容包含进来,一起进行编译运行.生成一个java文件.--%>
<%--格式:--%>
<%--<%@include file="相对路径或者是内部路径" %>--%>
<%--例如:--%>
<%--<%@include file="/jsp/include/i1.jsp" %>--%>

<%--路径:--%>
<%--相对路径:--%>
<%--./或者什么都不写 当前路径--%>
<%--上一级路径  ../--%>
<%--绝对路径:--%>
<%--带协议和主机的绝对路径--%>
<%--不带协议和主机的绝对路径--%>
<%--/项目名/资源--%>

<%--内部路径:--%>
<%--不带协议和主机的绝对路径去掉项目名--%>
<%--请求转发 静态包含 动态包含--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
include页面
<hr>
	i1的内容:<%@include file="/jsp/include/i1.jsp" %>
<hr>
	i1的内容:<%@include file="/jsp/include/i2.jsp" %>
</body>
</html>