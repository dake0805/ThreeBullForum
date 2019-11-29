<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/26
  Time: 上午9:24
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
            <li class="layui-nav-item">
                <a href="<c:url value="/admin/manageUsers"/>">
                    <i class="layui-icon"></i>
                    管理用户
                </a>
            </li>
            <li class="layui-nav-item layui-this">
                <a href="<c:url value="/admin/manageAdmins"/>">
                    <i class="layui-icon"></i>
                    管理admin
                </a>
            </li>
            <span class="layui-nav-bar" style="top: 167.5px; height: 0px; opacity: 0;"></span></ul>


        <div class="fly-panel fly-panel-user" pad20="">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <div class="layui-tab-title" id="LAY_mine">
                    <a href="<c:url value="/admin/addAdmin"/>">
                        <p style="line-height:40px; vertical-align: middle; float: left; margin-left: 10px">
                            <strong>增加管理员</strong></p>
                    </a>
                </div>

                <div class="layui-col-md-10">
                    <form method="POST" action="<c:url value="/admin/searchAdmin"/>">
                        <input style="width: 60%" name="username" class="text" type="text"
                               placeholder="输入你所要搜索的管理员用户名">
                        <input type="submit" style="width: 15%;" value="搜索"/>
                    </form>
                </div>

                <div class="layui-tab-content" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <ul class="mine-view jie-row">
                            <c:forEach items="${AdminList}" var="admin">
                                <li>
                                    <a class="jie-title" href="#" target="_blank">${admin.userName}</a>
                                    <a class="mine-edit" href="<c:url value="/admin/deleteAdmin/${admin.id}"/>">删除</a>
                                    <a class="mine-edit"
                                       href="<c:url value="/admin/editAdmin/${admin.id}"/>">编辑</a>
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

<script>
    var info = '${param.info}';
    if (info === 'userNameExist') {
        alert("用户名已存在!");
    }
    if (info === 'deleteSelf') {
        alert("删除操作不能删除自己!");
    }
</script>
