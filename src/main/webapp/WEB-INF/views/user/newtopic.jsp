<%--
  Created by IntelliJ IDEA.
  User: zcy10
  Date: 2019/11/24
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <div class="layui-form layui-form-pane">
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <form action="" method="post">
                        <div class="layui-row layui-col-space15 layui-form-item">

                            <div class="layui-col-md9">
                                <label for="L_title" class="layui-form-label">标题</label>
                                <div class="layui-input-block">
                                    <input type="text" id="L_title" name="title" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <div class="layui-input-block">
                                <div class="layui-unselect fly-edit"><span type="face" title="插入表情"><i
                                        class="iconfont icon-yxj-expression" style="top: 1px;"></i></span></div>
                                <textarea id="L_content" name="content" required="" lay-verify="required"
                                          placeholder="请输入主题内容"
                                          class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn" lay-filter="*" lay-submit="">立即发布</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>--%>
<%--    <title>newTopics</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">--%>
<%--</head>--%>
<%--<body>--%>
<%--<a href="<c:url value="/user/home"/>">返回首页</a>--%>
<%--<div class="spittleForm">--%>
<%--    <h1>发表新的主题</h1>--%>
<%--    <form method="POST" name="">--%>
<%--        <h2>主题名称</h2>--%>
<%--        <textarea name="title" cols="80" rows="5"></textarea><br/><br/>--%>
<%--        <h2>主题内容</h2>--%>
<%--        <textarea name="content" cols="80" rows="5"></textarea><br/><br/>--%>
<%--        <input type="submit" value="发表"/>--%>
<%--    </form>--%>
<%--</div>--%>
<%--&lt;%&ndash;<a href="<c:url value="/user/login" />">返回首页</a>&ndash;%&gt;--%>

<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
<%--    &lt;%&ndash;var info = '<%=request.getParameter("info")%>';&ndash;%&gt;--%>
<%--    var info = '${param.info}';--%>
<%--    if (info == 'empty_titleOrContent') {--%>
<%--        alert("主题或内容不能为空!");--%>
<%--    }--%>
<%--</script>--%>
