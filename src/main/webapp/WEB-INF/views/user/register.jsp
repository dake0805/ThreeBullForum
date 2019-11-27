<%--
  Created by IntelliJ IDEA.
  User: zy
  Date: 11/26/2019
  Time: 8:18 PM
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
    <div class="fly-panel fly-panel-user" pad20="">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="<c:url value="/user/login"/>">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
<%--            TODO 对应的control参数未一致--%>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_email" name="email" required="" lay-verify="email"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                            </div>

                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="pass" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="repass" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="*" lay-submit="">立即注册</button>
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
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>--%>
<%--    <title>🐂🐂🐂 > 注册</title>--%>
<%--    <link rel="stylesheet" type="text/css"--%>
<%--          href="<c:url value="/resources/css/style.css" />">--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>注册</h1>--%>

<%--<sf:form method="post" modelAttribute="user">--%>
<%--    <sf:errors path="*" cssClass="error"/>--%>
<%--    <br/>--%>
<%--    用户名：<sf:input path="userName"/>--%>
<%--    <sf:errors path="userName" cssClass="error"/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    　密码：<sf:password path="password"/>--%>
<%--    <sf:errors path="password" cssClass="error"/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <input type="submit" value="注册"/>--%>
<%--</sf:form>--%>
<%--</body>--%>
<%--</html>--%>

