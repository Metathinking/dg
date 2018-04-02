<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="inbox-body text-center">
    <%--<h4>用户中心</h4>--%>
    <img src="${USER_VO.userMsg==null||USER_VO.userMsg.icon==null?"${pageContext.request.contextPath}/res/img/head.png":USER_VO.userMsg.icon}"
         style="max-width: 100px;max-height: 100px" />
</div>
<ul class="inbox-nav inbox-divider">
    <li ng-class="{active:rightNav.index==1}">
        <a href="${pageContext.request.contextPath}/user/articleList.html"><i class="fa fa-file-text"></i> 我的文章 </a>
    </li>
    <li ng-class="{active:rightNav.index==2}">
        <a href="${pageContext.request.contextPath}/user/articleEdit.html"><i class="fa fa-pencil-square-o"></i>创建文章</a>
    </li>
    <li ng-class="{active:rightNav.index==3}">
        <a href="${pageContext.request.contextPath}/user/recommend/list.html"><i class="fa fa-certificate"></i>我的推广</a>
    </li>
    <li ng-class="{active:rightNav.index==4}">
        <a href="${pageContext.request.contextPath}/user/recommend/edit.html"><i class="fa fa-bookmark-o"></i>添加推广</a>
    </li>
    <li ng-class="{active:rightNav.index==5}">
        <a href="${pageContext.request.contextPath}/user/focusUser/list.html"><i class=" fa fa-heart-o"></i>关注的人</a>
    </li>
    <li ng-class="{active:rightNav.index==6}">
        <a href="${pageContext.request.contextPath}/user/fans/list.html"><i class="fa fa-users"></i>我的粉丝</a>
    </li>
    <li ng-class="{active:rightNav.index==7}">
        <a href="${pageContext.request.contextPath}/user/focusArticle/list.html"><i class=" fa fa-thumbs-o-up"></i>收藏的文章</a>
    </li>
    <li ng-class="{active:rightNav.index==8}">
        <a href="${pageContext.request.contextPath}/user/recycleBinArticle/list.html"><i class=" fa fa-trash-o"></i>回收站</a>
    </li>
    <li ng-class="{active:rightNav.index==9}">
        <a href="${pageContext.request.contextPath}/user/userMsg/edit.html"><i class=" fa fa-user"></i>个人信息</a>
    </li>
</ul>