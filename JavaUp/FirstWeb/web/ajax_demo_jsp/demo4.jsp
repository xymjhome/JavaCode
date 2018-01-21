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
		//页面加载成功 查询所有的省

		$.get("/FirstWeb/selectPro",function (d) {
			//alert(d);
            var $pro = $("#proId");
			$(d).each(function () {
                $pro.append($("<option value="+this.provinceid+">"+this.name+"</option>"));
            });
        },"json");
		
		
		//给省份下拉选派发change事件
        $("#proId").change(function () {
			//获取省份id
			var $pid = $(this).val();
			//alert($pid);
			//发送ajax请求 查询所有的市

			$.get("/FirstWeb/selectCity",{"pid":$pid},function (obj) {
				//alert(obj);
                var $cid = $("#cityId");
                $cid.html("<option>-请选择-</option>");
                if(obj!=null) {
                    $(obj).each(function () {
                        $cid.append($("<option value="+this.cityid+">"+this.name+"</option>"));
                    });
                }
            },"json");
        });

	})
</script>
<title>省市联动</title>
</head>
<body>
	<select id="proId" name="province">
		<option>-省份-</option>
		<!-- 
		<option value="1">北京</option>
		 -->
	</select>
	<select id="cityId" name="city">
		<option>-请选择-</option>
	</select>
</body>
</html>