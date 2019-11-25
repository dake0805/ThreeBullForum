<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>Three Bull Forum</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>登录有误，请重新尝试</h1>
<a href="<c:url value="/user/login" />">登录</a> |
<%--<a href="<c:url value="/spitter/register" />">注册</a>--%>
</body>
</html>
