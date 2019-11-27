<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/27
  Time: 8:52
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
<%@ include file="banner.jsp" %>
<div class="container">
    <div class="layui-col-md-10">
        <form method="POST" action="<c:url value="/searchTopic"/>">
         <span>
            <select style="width: 15%" name="type">
                <option value="TITLE">title</option>
                <option value="CONTENT">content</option>
                <option value="TITLEORCONTENT">title or content</option>
            </select>
        </span>
            <input style="width: 60%" name="info" class="text" type="text"
                   placeholder="输入你所要搜索的内容">
            <input type="submit" style="width: 15%;" value="搜索"/>
        </form>
        <div class="fly-panel">
            <ul class="fly-list">
                <c:forEach items="${AllTopics.items}" var="topic">
                    <li>
                        <h2>
                            <a href="<c:url value="/topic/detail/${topic.id}" />">${topic.title}</a>
                            <c:if test="${topic.topicStatus}">
                                <span class="layui-badge layui-bg-red">置顶中</span>
                            </c:if>
                            <c:if test="${not empty sessionScope.admin}">
                                <c:if test="${topic.topicStatus}">
                                    <a href="#"
                                       class="layui-badge layui-bg-cyan" style="float: right">取消置顶</a>
                                </c:if>
                                <c:if test="${not topic.topicStatus}">
                                    <a href="<c:url value="/admin/topTopic/${topic.id}"/>"
                                       class="layui-badge layui-bg-red" style="float: right">置顶</a>
                                </c:if>
                                <a href="<c:url value="/admin/editTopic/${topic.id}"/>"
                                   class="layui-badge layui-bg-blue" style="float: right">编辑帖子</a>
                                <a href="<c:url value="/admin/deleteTopic/${topic.id}"/>"
                                   class="layui-badge layui-bg-black" style="float: right">删除帖子</a>
                            </c:if>
                        </h2>
                        <div class="fly-list-info">
                            <a href="#" link="">
                                <cite>${topic.user.userName}</cite>
                            </a>
                                ${topic.postTime}
                            <span class="fly-list-nums">
                                        <i class="iconfont" title="点击">&#xe60b;</i> ${topic.clickNum}
                                        <i class="iconfont icon-pinglun1" title="回答"></i> ${topic.followNum}
                                      </span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div>
            每页${AllTopics.pageSize}条主题，
            第${AllTopics.currentPageNo}/${AllTopics.totalPageCount}页,共${AllTopics.totalCount}条主题
            <c:if test="${AllTopics.previousPage}">
                <a href="<c:url value="/?pageNo=${AllTopics.currentPageNo-1}" />">上一页</a>
            </c:if>
            <c:if test="${AllTopics.nextPage}">
                <a href="<c:url value="/?pageNo=${AllTopics.currentPageNo+1}" />">下一页</a>
            </c:if>
        </div>
    </div>
</div>
<!-- 引入footer文件-->
<%@ include file="footer.jsp" %>
</body>
</html>
