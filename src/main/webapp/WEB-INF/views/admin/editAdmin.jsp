<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/26
  Time: 上午9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Edit admin</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" >
</head>
<body>
<a href="<c:url value="/admin/home"/>">Return Home</a>
<div class="spittleForm">
    <h1>Please edit the information</h1>
    <form method="POST" name="">
        <h2>Username</h2>
        <input type="text" name="username" value="${SelectedAdmin.userName}"/>
        <h2>password</h2>
        <input type="text" name="password" value="${SelectedAdmin.password}"/>
        <input type="submit" value="Edit" />
    </form>
</div>
</body>
</html>
