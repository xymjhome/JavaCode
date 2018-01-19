<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--taglib指令:导入标签库--%>
<%--格式:--%>
<%--<%@taglib prefix="前缀名" uri="名称空间" %>--%>
<%--若导入之后--%>
<%--<前缀名:标签 .. >--%>
	<%--例如:--%>
	<%--<c:if test="">输出内容</c:if>--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${3>4 }">
		3大于4
	</c:if>
	<c:if test="${3<=4 }">
		3不大于4
	</c:if>
</body>
</html>