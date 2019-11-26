<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>欢迎
    <c:if test="${not empty sessionScope.admin && not empty sessionScope.admin.id }">
        ，<c:out value="${admin.userName}"/> <br/>
    </c:if>
</h1>

<h1>
    Admin Area
</h1>
<a href="<c:url value="/admin/manageTopics"/>">Manage Topics</a>
<a href="<c:url value="/admin/manageUsers"/>">Manage Users</a>
<a href="<c:url value="/admin/manageAdmins"/>">Manage Admins</a>

<h4>
    <a href="<c:url value="/admin/logout"/>">Logout</a>
</h4>

<br>
</body>
</html>
