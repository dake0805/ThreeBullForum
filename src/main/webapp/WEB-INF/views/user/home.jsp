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
    <c:if test = "${not empty sessionScope.user && not empty sessionScope.user.id }">
        ，<c:out value="${user.userName}" /> <br/>
    </c:if>
</h1>
<a href="<c:url value="/user/mytopics"/>">查看我的主题</a>
<a href="<c:url value="/user/newtopic"/>">新建主题</a>
<a href="<c:url value="/user/logout"/>">注销登录</a>
</body>
</html>
