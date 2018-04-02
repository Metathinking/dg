<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/3/9.0009
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="keywords" content="${siteInfo.keywords}" />
    <meta name="description" content="${siteInfo.description}" />
    <title>${siteInfo.name}-<tiles:insertAttribute name="title"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/roboto.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/material.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/bootstrap-material-design-mine.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/ripples.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dg.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dg_front.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/angular-1.5.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/dg.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-2.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/md5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/Utils.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/material.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/ripples.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/res/js/angular-file-upload.min.js"></script>
    <link rel="shortcut icon" href="${siteInfo.icon}"/>
    <script>
        var context = "${context}";
    </script>
</head>
<body ng-app="app">
<div class="container-fluid" ng-controller="userController">
    <div>
        <tiles:insertAttribute name="top-navigation"/>
    </div>
    <div style="margin-top: 100px">
        <div class="user-box">
            <aside class="lg-side well">
                <%--<div class="panel panel-default inbox-body">--%>
                    <tiles:insertAttribute name="content"/>
                <%--</div>--%>
            </aside>
            <aside class="sm-side well">
               <tiles:insertAttribute name="right-navigation"/>
            </aside>
        </div>
    </div>
</div>
<%--<div >--%>
    <tiles:insertAttribute name="footer"/>
    <%--&lt;%&ndash;${siteInfo.footer}&ndash;%&gt;--%>
<%--</div>--%>
</body>
</html>
<script>
    app.controller("userController",function($scope){
        $scope.nav={
            index:1
        };
        $scope.rightNav={
            index:1
        }
    })
</script>
<script>
    $.material.init();
</script>