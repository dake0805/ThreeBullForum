<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/26
  Time: 上午9:31
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%--<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
<%--    <title>Edit admin</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" >--%>
<%--</head>--%>
<%--<body>--%>
<%--<a href="<c:url value="/admin/home"/>">Return Home</a>--%>
<%--<div class="spittleForm">--%>
<%--    <h1>Please edit the information</h1>--%>
<%--    <form method="POST" name="">--%>
<%--        <h2>Username</h2>--%>
<%--        <h2>password</h2>--%>
<%--        <input type="submit" value="Edit" />--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
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
                <form method="post">
                    <div class="layui-form-item">
                        <label for="L_email" class="layui-form-label" style="text-align:left ">账号</label>
                        <div class="layui-input-inline">

                            <input type="text" name="username" id="L_email" value="${SelectedAdmin.userName}"
                                   required=""
                                   lay-verify="required"
                                   autocomplete="off" class="layui-input"/>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="L_pass" class="layui-form-label" style="text-align:left">密码</label>
                        <div class=" layui-input-inline">
                            <input type="text" id="L_pass" name="password" required="" lay-verify="required"
                                   autocomplete="off" class="layui-input" value="${SelectedAdmin.password}">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <button class="layui-btn" lay-filter="*" lay-submit="">确认修改</button>
                        <span style="padding-left:20px;"></span>
                    </div>
                    <a href="<c:url value="/admin/manageAdmins"/>"
                       class="layui-form-item layui-btn" style="padding-left:20px;">
                        返回
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 引入footer文件-->
<%@ include file="../footer.jsp" %>
</body>
</html>
