<%--
  Created by IntelliJ IDEA.
  User: zcy10
  Date: 2019/11/24
  Time: 11:17
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
    <title>newTopics</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
<div class="spittleForm">
    <h1>发表评论</h1>
    <form method="POST" name="spittleForm">
        <textarea name="name" cols="80" rows="5"></textarea><br/><br/>
        <textarea name="content" cols="80" rows="5"></textarea><br/><br/>
        <input type="submit" value="发表" />
    </form>
</div>
<%--<a href="<c:url value="/user/login" />">返回首页</a>--%>

</body>
</html>