<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/26
  Time: 上午9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>Manage Admins</title>
</head>
<body>
<a href="<c:url value="/admin/home"/>">Return Home</a>
<h1>
    Admin List
</h1>

<h3>
    <a href="<c:url value="/admin/addAdmin"/>">
        Add new admin
    </a>
</h3>

<div class="adminList">
    <form method="POST" action="<c:url value="/admin/searchAdmin"/>">
        <input name="username" class="text" type="text"
               placeholder="输入你所要搜索的用户名">
        <input type="submit" value="搜索"/>
    </form>
    <h1>All Admins</h1>
    <ul class="topicList">
        <c:forEach items="${AdminList}" var="admin">
            <div>
                Username: ${admin.userName}
                <a href="<c:url value="/admin/deleteAdmin/${admin.id}"/>">
                    Delete
                </a>
                <a href="<c:url value="/admin/editAdmin/${admin.id}"/>">
                    Edit
                </a>
            </div>
        </c:forEach>
    </ul>
</div>
<br>
</body>
</html>
