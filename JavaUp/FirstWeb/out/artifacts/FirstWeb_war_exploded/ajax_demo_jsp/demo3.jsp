<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		//文本框keyup的时候发送ajax
		$("#tid").keyup(function(){
			//获取文本框的值
			var $value=$(this).val();
			
			//内容为空的时候不发送ajax
			if($value!= null && $value!=''){
				//清空div
				$("#did").html("");
				
				$.post("/FirstWeb/searchKw","kw="+$value,function(d){
					//不为空的时候切割字符串
					if(d!=''){
						var arr=d.split(",");
						$(arr).each(function(){
							//alert(this);
							//可以将每一个值放入一个div 将其内部插入到id为did的div中
							$("#did").append($("<div>"+this+"</div>"));
						});
						//将div显示
						$("#did").show();
					}
				});
				
			}else{
				//内容为空的时候 将div隐藏 
				$("#did").hide();
			}
		});
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<center>
		<div>
			<h1>百度搜索</h1>
			<div>
				<input name="kw" id="tid"><input type="button" value="搜索一下">
			</div>
			<div id="did" style="border: 1px solid red;width: 171px;position:relative;left:-34px;display:none"></div>
		</div> 
	</center>
</body>
</html>