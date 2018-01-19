<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			var url="/FirstWeb/jqueryAjax";
			//var params="username=张三";
			var params={"username":"张苏纳"};
			//load方式
			/* $(this).load(url,params,function(d){
				alert(d);
			}); */
			
			//get方式
			/* $.get(url,params,function(d){
				alert(d)
			}); */
			
			//post方式
			/* $.post(url,params,function(d){
				alert(d.msg);
			},"json"); */
			
			//ajax 方式
			$.ajax({
				url:url,
				type:"post",
				data:params,
				success:function(d){
					alert(d.msg);
				},
				error:function(){},
				dataType:"json"
				
			});
		});		
	})
</script>
</head>
<body>
	<input type="button" id="btn" value="点我">
</body>
</html>