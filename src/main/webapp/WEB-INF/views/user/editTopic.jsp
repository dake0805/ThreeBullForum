<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ page session="true" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>主题</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<a href="<c:url value="/user/topic/${singleTopic.id}"/>">取消编辑</a>--%>
<%--<br>--%>
<%--<div class="spittleForm">--%>
<%--    <h1>编辑主题</h1>--%>
<%--    <form method="POST" name="TopicForm">--%>
<%--        主题：<br/>--%>
<%--        <textarea name="title" cols="80" rows="5"><c:out value="${singleTopic.title}"/></textarea>--%>
<%--        <br/>内容：<br/>--%>
<%--        <textarea name="content" cols="80" rows="10"><c:out value="${singleTopic.content}"/></textarea>--%>
<%--        <br/><br/>--%>
<%--        <input type="submit" value="确定"/>--%>
<%--    </form>--%>
<%--    <div class="topicList">--%>
<%--        评论：<br>--%>
<%--        <ul class="replyList">--%>
<%--            <c:forEach items="${replys.items}" var="reply">--%>
<%--                <li id="reply_<c:out value="${reply.id}"/>">--%>
<%--                    <div class="replyContent"><c:out value="${reply.content}"/></div>--%>
<%--                    <div class="replyTime">--%>
<%--                        <fmt:formatDate value="${reply.time}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--                        by <c:out value="${reply.user.userName }"/>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--    每页${replys.pageSize}条回复， 第${replys.currentPageNo}/${replys.totalPageCount}页,共${replys.totalCount}条回复--%>
<%--    <c:if test="${replys.previousPage}">--%>
<%--        <a href="<c:url value="/user/mytopics/${singleTopic.id}?pageNo=${replys.currentPageNo-1}" />">上一页</a>--%>
<%--    </c:if>--%>
<%--    <c:if test="${replys.nextPage}">--%>
<%--        <a href="<c:url value="/user/mytopics/${singleTopic.id}?pageNo=${replys.currentPageNo+1}" />">下一页</a>--%>
<%--    </c:if>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
<%--    &lt;%&ndash;var info = '<%=request.getParameter("info")%>';&ndash;%&gt;--%>
<%--    var info = '${param.info}';--%>
<%--    if (info == 'empty_titleOrContent') {--%>
<%--        alert("主题或内容不能为空!");--%>
<%--    }--%>
<%--</script>--%>
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
                <li class="layui-this"> 编辑帖子</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <form action="" method="post">
                        <div class="layui-row layui-col-space15 layui-form-item">

                            <div class="layui-col-md9">
                                <label for="L_title" class="layui-form-label">标题</label>
                                <div class="layui-input-block">
                                    <input type="text" id="L_title" name="title" required="" lay-verify="required"
                                           autocomplete="off" class="layui-input"
                                           value="<c:out value="${singleTopic.title}"/>"
                                    >
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <div class="layui-input-block">
                                <div class="layui-unselect fly-edit"><span type="face" title="插入表情"><i
                                        class="iconfont icon-yxj-expression" style="top: 1px;"></i></span></div>
                                <textarea id="L_content" name="content" required="" lay-verify="required"
                                          class="layui-textarea fly-editor" style="height: 260px;"><c:out
                                        value="${singleTopic.content}"/></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn" lay-filter="*" lay-submit="">
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
