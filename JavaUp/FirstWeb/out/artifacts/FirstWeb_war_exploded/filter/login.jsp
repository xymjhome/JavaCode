<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="${pageContext.request.contextPath }/login">
		<table>
			<tr>
				<td>用户名</td>
				<%--<td><input type="text" name="username" value="${cookie.savename.value}"></td>--%>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td colspan="1"><input type="checkbox" name="saveName" value="ok">记住用户名</td>
				<td colspan="1"><input type="checkbox" name="autoLogin" value="ok">自动登录</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"></td>
			</tr>

		</table>
	</form>
	${msg}
	<script type="text/javascript">
		onload=function(){//cookie中汉字解码
			var s="${cookie.savename.value }";
			s=decodeURI(s);
			alert(s);
			
			//将解码后的用户名付给username的文本框
			document.getElementsByName("username")[0].value=s;
			
		}
	</script>
</body>
</html>