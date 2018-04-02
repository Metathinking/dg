<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/7/6.0006
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card hovercard" ng-controller="userTopController" ng-init="findUser(${id})" style="margin-top: 80px">
    <div class="card-background">
    </div>
    <div class="useravatar">
        <img alt="" ng-src="{{userMsg==null||userMsg.icon==null?'${pageContext.request.contextPath}/res/img/head.png':userMsg.icon}}">
    </div>

    <div class="card-info"><span class="card-title"><div ng-bind="user.nickname" ></div></span>
    </div>
    <div style="position: absolute;right: 100px;bottom: 20px" ng-if="${USER==null}">
        <button type="button" class="btn btn-raised btn-primary"   data-toggle="modal" data-target="#loginModal">登录后关注</button>
    </div>
    <div style="position: absolute;right: 100px;bottom: 20px" ng-if="!own&&${USER!=null}">

        <button type="button" class="btn btn-raised btn-default" ng-click="changeFocus(true)" ng-if="!focusUser">关注</button>
        <button type="button" class="btn btn-raised btn-default" ng-click="changeFocus(false)" ng-if="focusUser">取消关注</button>
    </div>
</div>
<div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
    <div class="btn-group" role="group">
        <a  class="{{userNav.action==1?'btn btn-raised btn-primary':'btn btn-raised btn-default'}}" href="${pageContext.request.contextPath}/front/user/articleList/${id}.html" >
            <span class="glyphicon glyphicon-book" aria-hidden="true"></span>
            <div class="hidden-xs">文章</div>
        </a>
    </div>
    <div class="btn-group" role="group">
        <a class="{{userNav.action==2?'btn btn-raised btn-primary':'btn btn-raised btn-default'}}" href="${pageContext.request.contextPath}/front/user/focusUserList/${id}.html" >
            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
            <div class="hidden-xs">关注的人</div>
        </a>
    </div>
    <div class="btn-group" role="group">
        <a class="{{userNav.action==3?'btn btn-raised btn-primary':'btn btn-raised btn-default'}}" href="${pageContext.request.contextPath}/front/user/focusArticleList/${id}.html" >
            <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
            <div class="hidden-xs">收藏的文章</div>
        </a>
    </div>
    <div class="btn-group" role="group">
        <a  class="{{userNav.action==4?'btn btn-raised btn-primary':'btn btn-raised btn-default'}}" href="${pageContext.request.contextPath}/front/user/fansList/${id}.html" >
            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
            <div class="hidden-xs">粉丝</div>
        </a>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/frontUserService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/focusUserService.js"></script>
<script>
    app.controller("userTopController",function($scope,frontUserService,focusUserService){
        //初始化用户信息 start
        $scope.findUser = function(_id){
            frontUserService.findUserById(_id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
                            $scope.user = response.data.user;
                            $scope.own = response.data.own;
                            $scope.focusUser = response.data.focusUser;
                            $scope.userMsg = response.data.userMsg;
                            $scope.userAboutInfo = response.data.userAboutInfo;
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //初始化用户信息 end
        //关注、取消关注用户 start
        $scope.changeFocus = function(_focus){
            focusUserService.changeFocus($scope.user.id,_focus)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            if(response.data.changed){
                                $scope.userAboutInfo = response.data.userAboutInfo;
                                $scope.focusUser = response.data.focusUser;
                            }
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //关注、取消关注用户 end

    })
</script>
