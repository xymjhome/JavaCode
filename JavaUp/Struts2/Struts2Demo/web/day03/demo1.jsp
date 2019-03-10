<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 引入Struts2标签库 -->    
<%@ taglib prefix="s" uri="/struts-tags"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>条件：假如值栈中已经存入值了，在JSP页面上从值栈中获取值</h3>

<!--  
	1. 先引入Struts2框架提供的标签库，s标签
	2. 可以使用提供的标签（很多，掌握重点的标签）
-->

<!-- 从值栈中获取值的 value中间编写就是OGNL表达式 -->
<s:property value="'username'.length()"/> <br>
<s:property value="'hello'.length()"/>


</body>
</html>







