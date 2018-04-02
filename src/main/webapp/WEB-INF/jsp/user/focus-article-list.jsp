<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="focusArticleListController" ng-init="initList(${index})">
    <div ng-if="list.length>0">
        <table class="table table-striped table-hover">
            <tr>
                <td>序号</td>
                <td>标题</td>
                <td>标签</td>
                <td>时间</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td><a href="${pageContext.request.contextPath}/article/{{item.id}}.html" target="_blank" title="{{item.title}}">{{item.title}}</a> </td>
                <td>{{item.tag}}</td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <button class="btn btn-raised btn-default btn-sm" ng-if="!item.open" ng-click="changeFocus(item,false)">取消收藏</button>
                </td>
            </tr>
        </table>
        <div front-pagination-directive page-list="pageList" link="/user/focusArticle/list.html" page-count="pageQuery.pageCount" index="index"></div>
    </div>
    <div class="alert alert-info" ng-if="list.length==0">
        你还没有收藏任何文章
    </div>
        <div class="alert alert-info" ng-if="info">
            {{info}}
        </div>
        <div class="alert alert-danger" ng-if="error">
            {{error}}
        </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/focusArticleService.js"></script>
<script>
    app.controller('focusArticleListController', function ($scope, focusArticleService) {
        $scope.nav.index=4;
        $scope.rightNav.index=7;
        //初始化列表 start
        $scope.initList = function (_index) {
            $scope.index = _index;
            var data = {
                index: _index
            }
            focusArticleService.initList(data)
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

        //更改收藏状态 start
        $scope.changeFocus=function(_article,_focus){
            focusArticleService.changeFocus(_article.id,_focus)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.list.splice(_article,1);
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
    })
</script>