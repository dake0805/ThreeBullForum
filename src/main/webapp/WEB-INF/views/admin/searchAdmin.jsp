<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/26
  Time: 上午11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>搜索结果</title>
</head>
<body>
<div class="topicList">
    <ul class="topicList">
        <c:forEach items="${searchAdmins}" var="admin">
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
<a href="<c:url value="/admin/home"/>">返回主页</a>
</body>
</html>