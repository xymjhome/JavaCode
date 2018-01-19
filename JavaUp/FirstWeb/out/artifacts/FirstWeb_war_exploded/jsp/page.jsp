<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" isELIgnored="false" isErrorPage="false" errorPage="600.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--page指令:--%>
<%--重要属性:3个--%>
<%--contentType:设置响应流的编码,及通知浏览器用什么编码打开.设置文件的mimetype--%>
<%--pageEncoding:设置页面的编码--%>
<%--import:导入所需要的包--%>
<%--contentType和pageEncoding联系:--%>
<%--若两者都出现的时候,各自使用各自的编码--%>
<%--若只出现一者,两个都使用出现的这个编码--%>
<%--若两者都不出现,使用服务器默认的编码 tomcat7使用的iso-8859-1--%>
<%--了解属性:--%>
<%--language:当前jsp页面里面可以嵌套的语言--%>
<%--buffer:设置jsp页面的流的缓冲区的大小--%>
<%--autoFlush:是否自动刷新--%>
<%--extends:声明当前jsp的页面继承于那个类.必须继承的是httpservlet 及其子类--%>
<%--session:设置jsp页面是否可以使用session内置对象--%>
<%--isELIgnored:是否忽略el表达式--%>
<%--errorPage:当前jsp页面出现异常的时候要跳转到的jsp页面--%>
<%--isErrorPage:当前jsp页面是否是一个错误页面--%>
<%--若值为true,可以使用jsp页面的一个内置对象 exception--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.setAttribute("skey", "svalue");
	int i=1/0;
	
%>
<%=session.getAttribute("skey") %><br>
${sessionScope.skey }
</body>
</html>