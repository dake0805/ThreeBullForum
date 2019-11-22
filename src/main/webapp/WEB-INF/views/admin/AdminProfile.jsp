<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<html>
<head>
    <title>吐槽吧</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1>个人信息</h1>
用户名：<c:out value="${admin.userName}"/><br/><br/>
password：<c:out value="${admin.password}"/> <br/><br/>
id: <c:out value="${admin.id}"/> <br/><br/>

<c:if test="${not empty sessionScope.spitter }">
    <a href="<c:url value="/" />">首页</a>
</c:if>
</body>
</html>
