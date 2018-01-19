<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/18 0018
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示所有商品</title>
</head>
<body>
    <h1 align="center"><a href="${pageContext.request.contextPath}/add.jsp">添加商品</a></h1>
    <table border="1" align="center" width="88%">
        <tr>
            <td colspan="8" align="center">
                <form method="post" action="${pageContext.request.contextPath}/searchProduct">
                    商品名称：<input type="名字" name="name">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp关键词：<input type="名字" name="kw">&nbsp&nbsp&nbsp&nbsp
                    <input type="submit" value="搜索">
                </form>
            </td>
            <td colspan="1" align="right">
                <input type="button" value="删除选中商品" onclick="delCheck()">
            </td>
        </tr>
        <tr>
            <td><input type="checkbox" onclick="checkAll(this)"></td>
            <th>pid</th>
            <th>商品图片</th>
            <th>商品名称</th>
            <th>市场价</th>
            <th>商城价</th>
            <th>商品日期</th>
            <th>商品描述</th>
            <th>操作</th>
        </tr>
        <form id="formid" action="${pageContext.request.contextPath}/delCheckProduct" method="post">
            <c:forEach items="${plist}" var="p">
                <tr>
                    <td width="1%"><input type="checkbox" name="pid" value="${p.pid}"></td>
                    <td width="8%">${p.pid}</td>
                    <td width="8%"><img src="${pageContext.request.contextPath}/${p.pimage}" width="80"></td>
                    <td width="8%">${p.pname}</td>
                    <td width="8%">${p.market_price}</td>
                    <td width="8%">${p.shop_price}</td>
                    <td width="8%">${p.pdate}</td>
                    <td>${p.pdesc}</td>
                    <td width="8%">
                        <a href="${pageContext.request.contextPath}/getProductById?pid=${p.pid}">修改</a>
                        |
                        <a href="javascript:void(0)" onclick="deleteP('${p.pid}')">删除</a>
                    </td>

                </tr>
            </c:forEach>
        </form>
    </table>

</body>

<script type="text/javascript">

    //点击确认提示框后删除商品
    function deleteP(obj) {
        //alert(obj);
        if(confirm("确定删除吗？？")){
            location.href = "${pageContext.request.contextPath}/deleteProductById?pid="+obj;
        }
    }

    //选中所有商品的复选框
    function checkAll(obj){
       var check = document.getElementsByName("pid");
       for(var i = 0;i < check.length;i++)
       {
           check[i].checked = obj.checked;
       }
    }

    //提交商品表单到DelCheckProductServlet
    function delCheck(){
        if(confirm("确定删除选定商品吗？？")) {
            document.getElementById("formid").submit();
        }
    }
</script>
</html>
