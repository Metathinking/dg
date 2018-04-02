<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="recommendListController" ng-init="initList(${index})">
    <div ng-if="list.length>0" class="row">
        <table class="table table-striped table-hover" style="table-layout: fixed;word-break: break-all">
            <tr>
                <td>序号</td>
                <td>说明</td>
                <td>图片</td>
                <td>原地址</td>
                <td>推荐地址</td>
                <td>时间</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td>{{item.description}}</td>
                <td>
                    <a href="{{item.image}}" target="_blank">
                        <img ng-src="{{item.image}}" width="100"/>
                    </a>
                </td>
                <td>
                    <a href="{{item.shopUrl}}" target="_blank">
                        {{item.shopUrl}}
                    </a>
                </td>
                <td>
                    <a href="{{item.recommendUrl}}" target="_blank">
                        {{item.recommendUrl}}
                    </a>
                </td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <button class="btn btn-raised btn-primary btn-sm btn-option" ng-click="edit(item)" ><span class="glyphicon glyphicon-pencil"></span></button>
                    <button class="btn btn-raised btn-danger btn-sm btn-option" ng-click="delete(item,$index)" ><span class="glyphicon glyphicon-trash"></span></button>
                </td>
            </tr>
        </table>
        <div front-pagination-directive page-list="pageList" link="/user/recommend/list.html" page-count="pageQuery.pageCount" index="index"></div>
    </div>
    <div class="alert alert-info" ng-if="list.length==0">
        你还没有添加任何推广
    </div>
    <div class="alert alert-info" ng-if="info">
        {{info}}
    </div>
    <div class="alert alert-danger" ng-if="error">
        {{error}}
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/recommendService.js"></script>
<script>
    app.controller('recommendListController', function ($scope, recommendService) {
        $scope.nav.index=4;
        $scope.rightNav.index=3;
        //初始化列表 start
        $scope.initList = function (_index) {
            $scope.index = _index;
            recommendService.initList(_index)
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

        //编辑 start
        $scope.edit=function(_recommend){
            window.location.href="${pageContext.request.contextPath}/user/recommend/edit.html?id="+_recommend.id;
        }
        //编辑 end

        //删除 start
        $scope.delete = function(_recommend,_index){
            recommendService.delete(_recommend.id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error="";
                            $scope.list.splice(_index,1);
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //删除 end
    })
</script>