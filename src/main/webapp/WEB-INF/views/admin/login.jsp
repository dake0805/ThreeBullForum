<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page session="true" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>吐槽吧</title>--%>
<%--    <link rel="stylesheet" type="text/css"--%>
<%--          href="<c:url value="/resources/css/style.css" />" >--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>登录</h1>--%>
<%--<form method="POST">--%>
<%--    用户名: <input type="text" name="userName" /><br/><br/>--%>
<%--    　密码: <input type="password" name="password" /><br/><br/>--%>
<%--    <input type="submit" value="登录" />--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>吐槽吧</title>--%>
<%--    <link rel="stylesheet" type="text/css"--%>
<%--          href="<c:url value="/resources/css/style.css" />" >--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>登录</h1>--%>
<%--<form method="POST">--%>
<%--    用户名: <input type="text" name="userName" /><br/><br/>--%>
<%--    　密码: <input type="password" name="password" /><br/><br/>--%>
<%--    <input type="submit" value="登录" />--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

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
<%--admin特殊页面--%>
<header>
    <nav class="navbar navbar-default" role="navigation" style="background-color: white">
        <div class="container-fluid" style="margin-left: 10%">
            <div class="navbar-header">
                <a class="navbar-brand" href="<c:url value="/"/>">ThreeBull Fourm</a>
            </div>
            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <p class="navbar-text"><a href="<c:url value="/admin/login"/>">登录</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container">
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_email" name="userName" value="${cookie.admin.value}" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="password" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit="">立即登录</button>
                                <span style="padding-left:20px;">
                </span>
                            </div>

                        </form>
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