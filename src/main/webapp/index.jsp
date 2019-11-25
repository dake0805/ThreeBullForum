<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<html>
<body>
<h2>Hello World!</h2>
<br>
<a href="<c:url value="/admin/login"/>">Admin Login</a>
<a href="<c:url value="/user/login"/>">User登录</a>
<%--<a href="<c:url value="/user/mytopics"/>">查看我的主题</a>--%>
</body>
</html>
