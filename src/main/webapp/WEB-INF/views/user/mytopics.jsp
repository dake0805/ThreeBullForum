<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>我发表过的主题</title>
</head>
<body>
<h1>欢迎
    <c:if test="${not empty sessionScope.user && not empty sessionScope.user.id }">
        ，<c:out value="${user.userName}"/> <br/>
    </c:if>
</h1>
<div class="topicList">
    <h1>我发表过的主题</h1>
    <ul class="topicList">
        <c:forEach items="${MyTopics.items}" var="topic">
            <li id="topic_<c:out value="${topic.id}"/>">
                <div class="topicTitle"><a href="<c:url value="/user/topic/${topic.id}" />"><c:out
                        value="${topic.title}"/></a></div>
                <div class="topicTime">
                    <fmt:formatDate value="${topic.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    by <c:out value="${topic.user.userName }"/>
                    点击数：<c:out value="${topic.clickNum }"/>
                    回复数：<c:out value="${topic.followNum }"/>
                    <c:if test="${topic.topicStatus}">
                        置顶中，置顶时间:
                        <fmt:formatDate value="${topic.topTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </c:if>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
每页${MyTopics.pageSize}条主题， 第${MyTopics.currentPageNo}/${MyTopics.totalPageCount}页,共${MyTopics.totalCount}条主题
<c:if test="${MyTopics.previousPage}">
    <a href="<c:url value="/user/mytopics?pageNo=${MyTopics.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${MyTopics.nextPage}">
    <a href="<c:url value="/user/mytopics?pageNo=${MyTopics.currentPageNo+1}" />">下一页</a>
</c:if>
<br>
<a href="<c:url value="/user/home"/>">返回主页</a>
</body>
</html>
