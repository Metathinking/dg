<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="fansListController" ng-init="getFansList(${index})">
    <div ng-if="list.length>0">

        <div class="row">
            <div class="col-md-2 user-item" ng-repeat="userVO in list">
                <img ng-src="{{userVO.userMsg.icon?userVO.userMsg.icon:'/res/img/head.png'}}"
                     title="{{userVO.user.nickname}}"/>
                <br/>
                <a href="${pageContext.request.contextPath}/front/user/articleList/{{userVO.user.id}}.html" target="_blank">
                    <div ng-bind="userVO.user.nickname"></div>
                </a>
                <br/>
                <button type="button" class="btn btn-raised btn-default btn-sm"
                        ng-click="changeFocus(userVO.user.id,$index)">取消关注
                </button>
            </div>
        </div>
        <div front-pagination-directive page-list="pageList" link="/user/fans/list.html"
             page-count="pageQuery.pageCount" index="index"></div>

    </div>
    <div class="alert alert-info" ng-if="list.length==0">
        你还没有粉丝
    </div>
    <div class="alert alert-info" ng-if="info">
        {{info}}
    </div>
    <div class="alert alert-danger" ng-if="error">
        {{error}}
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/focusUserService.js"></script>
<script>
    app.controller('fansListController', function ($scope, focusUserService) {
        $scope.nav.index = 4;
        $scope.rightNav.index = 6;
        //初始化列表 start
        $scope.getFansList = function (_index) {
            $scope.index = _index;
            var data = {
                index: _index
            }
            focusUserService.getFansList(data)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.list = response.data.list;
                            $scope.pageQuery = response.data.pageQuery;
                            $scope.pageList = [];
                            for (var i = 1; i < $scope.pageQuery.pageCount + 1; i++) {
                                $scope.pageList.push(i);
                            }
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //初始化列表 end

    })
</script>