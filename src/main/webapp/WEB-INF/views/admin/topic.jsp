<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
<head>
    <title>主题</title>
</head>
<body>
<a href="<c:url value="/admin/manageTopics"/>">Return</a>
<br>
<div class="topicForm">
    <h2>主题:
        <c:out value="${singleTopic.title}"/>
    </h2>
    <br/><br/>
    <h3>
        内容:
        <c:out value="${singleTopic.content}"/>
    </h3>
    <br/><br/>
    <a href="<c:url value="/admin/editTopic/${singleTopic.id}"/>"> 编辑主题内容</a>
    <div class=" topicList">
        评论：<br>
        <ul class="replyList">
            <c:forEach items="${replys.items}" var="reply">
                <li id="reply_<c:out value="${reply.id}"/>">
                    <div class="replyContent"><c:out value="${reply.content}"/></div>
                    <div class="replyTime">
                        <fmt:formatDate value="${reply.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        by <c:out value="${reply.user.userName }"/>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="spittleForm">
        <h1>发表新的评论</h1>
        <form method="POST" name="newReplay">
            <h2>评论内容</h2>
            <textarea name="content" cols="80" rows="5"></textarea><br/><br/>
            <input type="submit" value="发表"/>
        </form>
    </div>


    每页${replys.pageSize}条回复， 第${replys.currentPageNo}/${replys.totalPageCount}页,共${replys.totalCount}条回复
    <c:if test="${replys.previousPage}">
        <a href="<c:url value="/admin/topic/${singleTopic.id}?pageNo=${replys.currentPageNo-1}" />">上一页</a>
    </c:if>
    <c:if test="${replys.nextPage}">
        <a href="<c:url value="/admin/topic/${singleTopic.id}?pageNo=${replys.currentPageNo+1}" />">下一页</a>
    </c:if>
</div>
</body>
</html>
