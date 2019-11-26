<%--
  Created by IntelliJ IDEA.
  User: kehan
  Date: 2019/11/25
  Time: 下午8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>Manage Topics</title>
</head>
<body>
<a href="<c:url value="/admin/home"/>">Return Home</a>
<h1>
    Topic List
</h1>
<div class="topicList">
    <form method="POST" action="<c:url value="/admin/searchTopic"/>">
        <span>
            <select name="type">
                <option value="TITLE">title</option>
                <option value="CONTENT">content</option>
                <option value="TITLEORCONTENT">title or content</option>
            </select>
        </span>
        <input name="info" class="text" type="text"
               placeholder="输入你所要搜索的内容">
        <input type="submit" value="搜索"/>
    </form>
    <h1>所有主题</h1>
    <ul class="topicList">
        <c:forEach items="${AllTopics.items}" var="topic">
            <li id="topic_<c:out value="${topic.id}"/>">
                <div class="topicTitle"><a href="<c:url value="/admin/topic/${topic.id}" />"><c:out
                        value="${topic.title}"/></a></div>
                <div class="topicTime">
                    <fmt:formatDate value="${topic.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    by <c:out value="${topic.user.userName }"/>
                    点击数：<c:out value="${topic.clickNum }"/>
                    回复数：<c:out value="${topic.followNum }"/>
                    <a href="<c:url value="/admin/deleteTopic/${topic.id}"/>">
                        Delete
                    </a>
                    <a href="<c:url value="/admin/editTopic/${topic.id}"/>">
                        Edit
                    </a>
                    <a href="<c:url value="/admin/topTopic/${topic.id}"/>">
                        Top
                    </a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
每页${AllTopics.pageSize}条主题， 第${AllTopics.currentPageNo}/${AllTopics.totalPageCount}页,共${AllTopics.totalCount}条主题
<c:if test="${AllTopics.previousPage}">
    <a href="<c:url value="/user/home?pageNo=${AllTopics.currentPageNo-1}" />">上一页</a>
</c:if>
<c:if test="${AllTopics.nextPage}">
    <a href="<c:url value="/user/home?pageNo=${AllTopics.currentPageNo+1}" />">下一页</a>
</c:if>
<br>
</body>
</html>
