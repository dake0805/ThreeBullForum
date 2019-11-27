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
                <a href="<c:url value="/admin/manageUsers"/>">
                    <i class="layui-icon"></i>
                    管理admin
                </a>
            </li>
            <span class="layui-nav-bar" style="top: 167.5px; height: 0px; opacity: 0;"></span></ul>


        <div class="fly-panel fly-panel-user" pad20="">
            <div class="layui-tab layui-tab-brief" lay-filter="user">

                <div class="layui-tab-content" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <ul class="mine-view jie-row">
                            <li>
                                <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
                                <i>2017/3/14 上午8:30:00</i>
                                <a class="mine-edit" href="/jie/edit/8116">编辑</a>
                                <em>661阅/10答</em>
                            </li>
                            <li>
                                <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
                                <i>2017/3/14 上午8:30:00</i>
                                <a class="mine-edit" href="/jie/edit/8116">编辑</a>
                                <em>661阅/10答</em>
                            </li>
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
