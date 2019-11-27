<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<html>
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
    <div class="">
        <div class="fly-panel detail-box">
            <h1>${singleTopic.title}</h1>
            <div class="fly-detail-info">
                <c:if test="${singleTopic.topicStatus}">
                    <span class="layui-badge layui-bg-red">置顶</span>
                </c:if>
                <span class="fly-list-nums">
                                                            <i class="iconfont"
                                                               title="点击">&#xe60b;</i> ${singleTopic.clickNum}
                                        <i class="iconfont icon-pinglun1" title="回答"></i> ${singleTopic.followNum}
          </span>
            </div>
            <div class="detail-about">
                <div class="fly-detail-user">
                    <a href="../user/home.html" class="fly-link">
                        <cite>${singleTopic.user.userName}</cite>
                    </a>
                    <span>${singleTopic.postTime}</span>
                </div>
                <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                    <c:if test="${isMyself}">
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                    </c:if>
                </div>
            </div>
            <div class="detail-body photos">
                <hr>
                <p>
                    <c:out value="${singleTopic.content}"/>
                </p>
            </div>
        </div>

        <div class="fly-panel detail-box" id="flyReply">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                <legend>回帖</legend>
            </fieldset>
            <ul class="jieda" id="jieda">
                <c:forEach items="${replys.items}" var="reply">
                    <li data-id="111">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>${reply.user.userName}</cite>
                                </a>
                            </div>
                            <div class="detail-hits">
                                <span>${reply.time}</span>
                            </div>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p> ${reply.content}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            每页${replys.pageSize}条回复， 第${replys.currentPageNo}/${replys.totalPageCount}页,共${replys.totalCount}条回复
            <c:if test="${replys.previousPage}">
                <a href="<c:url value="/?pageNo=${replys.currentPageNo-1}" />">上一页</a>
            </c:if>
            <c:if test="${replys.nextPage}">
                <a href="<c:url value="/?pageNo=${replys.currentPageNo+1}" />">下一页</a>
            </c:if>
            <br>
            <br>
            <c:if test="${not empty sessionScope.user && not empty sessionScope.user.id}">
                <div class="layui-form layui-form-pane">
                    <form action="/jie/reply/" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <div class="layui-unselect fly-edit"><span type="face" title="插入表情"><i
                                        class="iconfont icon-yxj-expression" style="top: 1px;"></i></span><span
                                        type="picture" title="插入图片：img[src]"><i
                                        class="iconfont icon-tupian"></i></span><span type="href"
                                                                                      title="超链接格式：a(href)[text]"><i
                                        class="iconfont icon-lianjie"></i></span><span type="code" title="插入代码或引用"><i
                                        class="iconfont icon-emwdaima" style="top: 1px;"></i></span><span type="hr"
                                                                                                          title="插入水平线">hr</span><span
                                        type="yulan" title="预览"><i class="iconfont icon-yulan1"></i></span></div>
                                <textarea id="L_content" name="content" required="" lay-verify="required"
                                          placeholder="请输入内容" class="layui-textarea fly-editor"
                                          style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button class="layui-btn" lay-filter="*" lay-submit="">提交回复</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<%--<script>--%>
<%--    &lt;%&ndash;var info = '<%=request.getParameter("info")%>';&ndash;%&gt;--%>
<%--    var info = '${param.info}';--%>
<%--    if (info == 'empty_content') {--%>
<%--        alert("回帖内容不能为空!");--%>
<%--    }--%>
<%--</script>--%>
