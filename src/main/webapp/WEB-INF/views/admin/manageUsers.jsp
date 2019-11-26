<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/25
  Time: 下午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>
<a href="<c:url value="/admin/home"/>">Return Home</a>
<h1>
    User List
</h1>
<div class="userList">
    <%--    <form method="POST" action="<c:url value="/user/searchTopic"/>">--%>
    <%--        <span>--%>
    <%--            <select name="type">--%>
    <%--                <option value="TITLE">title</option>--%>
    <%--                <option value="CONTENT">content</option>--%>
    <%--                <option value="TITLEORCONTENT">title or content</option>--%>
    <%--            </select>--%>
    <%--        </span>--%>
    <%--        <input name="info" class="text" type="text"--%>
    <%--               placeholder="输入你所要搜索的内容">--%>
    <%--        <input type="submit" value="搜索"/>--%>
    <%--    </form>--%>
    <h1>All Users</h1>
    <ul class="topicList">
        <c:forEach items="${AllUsers}" var="user">
            <div>
                Username: ${user.userName}
                <a href="<c:url value="/admin/lockUser/${user.id}"/>">
                    Lock
                </a>
            </div>
        </c:forEach>
    </ul>
</div>
<br>
</body>
</html>