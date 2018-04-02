<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-wrapper">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/userManager/list.html">买东西——后台管理</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li ng-class="{active:nav.index==1}"><a href="${pageContext.request.contextPath}/admin/userManager/list.html">用户管理</a></li>
                        <li ng-class="{active:nav.index==2}"><a href="${pageContext.request.contextPath}/admin/articleManager/list.html">文章管理</a></li>
                        <li ng-class="{active:nav.index==4}"><a href="${pageContext.request.contextPath}/admin/email/send.html">邮件管理</a></li>
                        <li ng-class="{active:nav.index==5}"><a href="${pageContext.request.contextPath}/admin/siteInfo/edit.html">网站设置</a></li>
                        <li ng-class="{active:nav.index==6}"><a href="${pageContext.request.contextPath}/">查看首页</a></li>
                    </ul>
                    <ul class="nav navbar-nav pull-right">
                        <li class=""><a href="${pageContext.request.contextPath}/adminLogin/logout.html">退出</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
