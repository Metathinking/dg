<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container" style="padding-left: 80px;padding-right: 80px">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">我来说</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/" class="">首页</a></li>
                        <c:forEach var="column" items="${columnList}">
                            <li><a href="${column.url}">${column.name}</a></li>
                        </c:forEach>


                    </ul>
                    <ul class="nav navbar-nav pull-right" ng-if="${USER==null}">
                        <li ng-class="{active:nav.index==2}"><a href="${pageContext.request.contextPath}/login.html">登录</a></li>
                        <li ng-class="{active:nav.index==3}"><a href="${pageContext.request.contextPath}/register.html">注册</a></li>
                    </ul>
                    <ul class="nav navbar-nav pull-right" ng-if="${USER!=null}">
                        <li class="dropdown" ng-class="{active:nav.index==4}">
                            <a  class="dropdown-toggle" data-toggle="dropdown">
                                ${USER.nickname}
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/user/articleList.html">用户中心</a></li>
                                <li><a href="${pageContext.request.contextPath}/logout.html">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>