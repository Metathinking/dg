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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html ng-app="app" >
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <%--<meta name="keywords" content="{{base.keywords}}" />--%>
    <%--<meta name="description" content="{{base.description}}" />--%>
    <%--<title>{{base.title}}</title>--%>
    <meta name="keywords" content="${keywords==null?siteInfo.keywords:keywords}" />
    <meta name="description" content="${description==null?siteInfo.description:description}" />
    <title>${title==null?siteInfo.name:title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/roboto.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/material.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/bootstrap-material-design-mine.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/ripples.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dg.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dg_front.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/angular-1.5.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/angular-cookies.js"></script>
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
    <script>
        app.controller("frontController",function($scope){
            $scope.nav={
                index:1
            };
            $scope.rightNav={
                index:1
            }
            $scope.userNav={
                action:1
            }
        })
    </script>
</head>
<body  >
<div class="container" ng-controller="frontController">
    <div>
        <tiles:insertAttribute name="top-navigation"/>
    </div>
    <div>
        <tiles:insertAttribute name="user-top"/>
    </div>
    <div style="margin-top: <tiles:insertAttribute name="margin-top"/>">
        <tiles:insertAttribute name="content"/>
    </div>

    <%--<i class="fa fa-bug fa-2x" title="Report Bug"></i>--%>
    <div>
        <tiles:insertAttribute name="loginModal"/>
    </div>
</div>
<div >
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>

<script>
    $.material.init();
</script>