<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/13.0013
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="nav-side-menu">
    <div class="brand">邮件管理</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
    <div class="menu-list">
        <ul id="menu-content" class="menu-content collapse out">
            <li ng-class="{active:leftNav.index==1}">
                <a href="${pageContext.request.contextPath}/admin/email/send.html">
                    <i class="fa fa-send fa-lg"></i>邮件发送
                </a>
            </li>
            <li ng-class="{active:leftNav.index==2}">
                <a href="${pageContext.request.contextPath}/admin/email/list.html">
                    <i class="fa fa-list fa-lg"></i>发送记录
                </a>
            </li>
            <li ng-class="{active:leftNav.index==3}">
                <a href="${pageContext.request.contextPath}/admin/emailSetting/edit.html">
                    <i class="fa fa-gear fa-lg"></i>邮件设置
                </a>
            </li>
            <li ng-class="{active:leftNav.index==4}">
                <a href="${pageContext.request.contextPath}/admin/emailMsgTemplate/edit.html">
                    <i class="fa fa-file-text fa-lg"></i>信息模板编辑
                </a>
            </li>
            <li ng-class="{active:leftNav.index==5}">
                <a href="${pageContext.request.contextPath}/admin/emailMsgTemplate/list.html">
                    <i class="fa fa-list-alt fa-lg"></i>信息模板
                </a>
            </li>
        </ul>
    </div>
</div>
