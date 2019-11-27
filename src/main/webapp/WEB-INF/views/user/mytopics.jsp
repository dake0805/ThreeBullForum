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
<%@ include file="../banner.jsp" %>
<div class="container">
    <div class="layui-col-md-10">
        <div class="fly-panel">
            <ul class="fly-list">
                <c:forEach items="${MyTopics.items}" var="topic">
                    <li>
                        <h2>
                            <a href="<c:url value="/topic/detail/${topic.id}" />">${topic.title}</a>
                            <c:if test="${topic.topicStatus}">
                                <span class="layui-badge layui-bg-red">置顶</span>
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
                </li>
            </ul>
        </div>
        <div>
            每页${MyTopics.pageSize}条主题，
            第${AllTopics.currentPageNo}/${MyTopics.totalPageCount}页,共${MyTopics.totalCount}条主题
            <c:if test="${MyTopics.previousPage}">
                <a href="<c:url value="/user/mytopics?pageNo=${MyTopics.currentPageNo-1}" />">上一页</a>
            </c:if>
            <c:if test="${MyTopics.nextPage}">
                <a href="<c:url value="/user/mytopics?pageNo=${MyTopics.currentPageNo+1}" />">下一页</a>
            </c:if>
        </div>
    </div>
</div>

<!-- 引入footer文件-->
<%@ include file="../footer.jsp" %>
</body>
</html>
