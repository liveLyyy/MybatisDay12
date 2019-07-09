<%--
  Created by IntelliJ IDEA.
  User: 86374
  Date: 2019/7/9
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
 <tr>
     <th>编号</th>
     <th>姓名</th>
     <th>年龄</th>
 </tr>
    <c:forEach var="page" items="${pageInfo.list}" >
        <tr>
            <td>${page.id}</td>
            <td>${page.name}</td>
            <td>${page.age}</td>
        </tr>
    </c:forEach>

</table>
<a href="pa?pageNumber=${pageInfo.pageNumber-1}&pageSize=${pageInfo.pageSize}"   <c:if test="${pageInfo.pageNumber<=1}"> onclick="javascript:return false;" </c:if>>上一页</a>
<a href="pa?pageNumber=${pageInfo.pageNumber+1}&pageSize=${pageInfo.pageSize}"  <c:if test="${pageInfo.pageNumber>=pageInfo.total}"> onclick="javascript:return false;" </c:if>>下一页</a>
</body>
</html>
