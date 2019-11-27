<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <nav class="navbar navbar-default" role="navigation" style="background-color: white">
        <div class="container-fluid" style="margin-left: 10%">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">ThreeBull Fourm</a>
            </div>
            <div>
                <c:if test="${empty user && empty admin}">
                    <!--未登陆-->
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <p class="navbar-text"><a href="<c:url value="/user/login"/>">登录</a></p>
                        </li>
                        <li>
                            <p class="navbar-text"><a href="/#">注册</a></p>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${not empty user }">
                    <!--已登陆-->
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <p class="navbar-text">欢迎，${user.userName}</p>
                        </li>
                        <li>
                            <p class="navbar-text"><a href="<c:url value="/user/logout"/>">登出</a></p>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${not empty admin}">
                    <!--已登陆-->
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <p class="navbar-text">欢迎，${admin.adminName}</p>
                        </li>
                        <li>
                            <p class="navbar-text"><a href="<c:url value="/admin/logout"/>">登出</a></p>
                    </ul>
                </c:if>
            </div>
        </div>
    </nav>
</header>