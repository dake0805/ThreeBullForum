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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 引入header文件 -->
<%@ include file="../banner.jsp" %>
<div class="container">
    <div class="layui-container fly-marginTop fly-user-main">
        <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
            <li class="layui-nav-item layui-this">
                <a href="<c:url value="/admin/manageUsers"/>">
                    <i class="layui-icon"></i>
                    管理用户
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="<c:url value="/admin/manageAdmins"/>">
                    <i class="layui-icon"></i>
                    管理admin
                </a>
            </li>

            <span class="layui-nav-bar" style="top: 167.5px; height: 0px; opacity: 0;"></span></ul>


        <div class="fly-panel fly-panel-user" pad20="">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <div class="layui-tab-title" id="LAY_mine">
                    <%--                    <a href="librarian_add.jsp">--%>
                    <%--                        <p style="line-height:40px; vertical-align: middle; float: left; margin-left: 10px">--%>
                    <%--                            <strong>增加用户</strong></p>--%>
                    <%--                    </a>--%>
                    <strong>用户列表</strong>
                </div>
                <div class="layui-tab-content" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <ul class="mine-view jie-row">
                            <c:forEach items="${AllUsers}" var="user">
                                <li>
                                    <a class="jie-title" href="#" target="_blank">${user.userName}</a>
                                    <c:if test="${not user.isLocked}">
                                        <a class="mine-edit" href="<c:url value="/admin/lockUser/${user.id}"/>">上锁</a>
                                    </c:if>
                                    <c:if test="${user.isLocked}">
                                        <a class="mine-edit" href="<c:url value="/admin/unLockUser/${user.id}"/>">解锁</a>
                                    </c:if>
                                        <%--                                    <a class="mine-edit" href="/admin/deleteUser/">删除</a>--%>
                                        <%--                                    <a class="mine-edit" href="/jie/edit/8116">编辑</a>--%>
                                </li>
                            </c:forEach>
                        </ul>
                        <div id="LAY_page"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入footer文件-->
<%@ include file="../footer.jsp" %>
</body>
</html>
