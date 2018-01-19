<%--
  Created by IntelliJ IDEA.
  User: ljt
  Date: 2018/1/18 0018
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分页展示商品</title>
</head>
<body>
    <table border="1" align="center" width="88%">
        <tr>
            <th>pid</th>
            <th>商品图片</th>
            <th>商品名称</th>
            <th>市场价</th>
            <th>商城价</th>
            <th>商品日期</th>
            <th>商品描述</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.list}" var="p">
            <tr>
                <td width="1%"><input type="checkbox" name="pid" value="${p.pid}"></td>
                <td width="8%">${p.pid}</td>
                <td width="8%"><img src="${pageContext.request.contextPath}/${p.pimage}" width="80"></td>
                <td width="8%">${p.pname}</td>
                <td width="8%">${p.market_price}</td>
                <td width="8%">${p.shop_price}</td>
                <td width="8%">${p.pdate}</td>
                <td>${p.pdesc}</td>
            </tr>
        </c:forEach>
    </table>
    <center>
        <!--
            扩展:页面多的时候 采用前五后四的方式
            只需要控制begin和end
            begin 判断当前页-5>0?当前页-5:1
            end 判断 当前页+4>总页数?总页数:当前页+4
        -->
        <!-- 若是第一页 首页和上一页不展示-->
        <c:if test="${page.currPage!=1 }">
            <a href='${pageContext.request.contextPath}/getPageProduct?currPage=1'>[首页]  </a>
            <a href='${pageContext.request.contextPath}/getPageProduct?currPage=${page.currPage-1}'>[上一页]</a>
        </c:if>


        <c:if test="${page.currPage - 5 <= 0 && page.currPage + 4 <= page.totalPage}" >
            <!-- 将所有的页码展示出来 -->
            <c:forEach begin="1" end="${page.currPage + 4 }" var="n">
                <!-- 若是当前页 不可点 -->
                <c:if test="${page.currPage == n}">
                    ${n}
                </c:if>

                <!-- 若不是当前页 可点 -->
                <c:if test="${page.currPage != n}">
                    <a href='${pageContext.request.contextPath}/getPageProduct?currPage=${n}'>${n}</a>
                </c:if>
            </c:forEach>
        </c:if>

        <c:if test="${page.currPage - 5 > 0 && page.currPage + 4 <= page.totalPage}" >
            <!-- 将所有的页码展示出来 -->
            <c:forEach begin="${page.currPage - 5}" end="${page.currPage + 4 }" var="n">
                <!-- 若是当前页 不可点 -->
                <c:if test="${page.currPage == n}">
                    ${n}
                </c:if>

                <!-- 若不是当前页 可点 -->
                <c:if test="${page.currPage != n}">
                    <a href='${pageContext.request.contextPath}/getPageProduct?currPage=${n}'>${n}</a>
                </c:if>
            </c:forEach>
        </c:if>

        <c:if test="${page.currPage - 5 > 0 && page.currPage + 4 > page.totalPage}" >
            <!-- 将所有的页码展示出来 -->
            <c:forEach begin="${page.currPage - 5}" end="${page.totalPage }" var="n">
                <!-- 若是当前页 不可点 -->
                <c:if test="${page.currPage == n}">
                    ${n}
                </c:if>

                <!-- 若不是当前页 可点 -->
                <c:if test="${page.currPage != n}">
                    <a href='${pageContext.request.contextPath}/getPageProduct?currPage=${n}'>${n}</a>
                </c:if>
            </c:forEach>
        </c:if>

        <c:if test="${page.currPage - 5 <= 0 && page.currPage + 4 > page.totalPage}" >
            <!-- 将所有的页码展示出来 -->
            <c:forEach begin="1" end="${page.totalPage }" var="n">
                <!-- 若是当前页 不可点 -->
                <c:if test="${page.currPage == n}">
                    ${n}
                </c:if>

                <!-- 若不是当前页 可点 -->
                <c:if test="${page.currPage != n}">
                    <a href='${pageContext.request.contextPath}/getPageProduct?currPage=${n}'>${n}</a>
                </c:if>
            </c:forEach>
        </c:if>





        <!-- 若是最后一页 尾页和下一页不展示 -->
        <c:if test="${page.currPage!=page.totalPage}">
            <a href="${pageContext.request.contextPath}/getPageProduct?currPage=${page.currPage + 1}">[下一页]</a>
            <a href="${pageContext.request.contextPath}/getPageProduct?currPage=${page.totalPage}">[尾页]</a>
        </c:if>


    </center>
</body>
</html>
