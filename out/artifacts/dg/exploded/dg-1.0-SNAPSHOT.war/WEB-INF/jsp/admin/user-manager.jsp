<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/8.0008
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-controller="userController">
    <div class="alert alert-danger" ng-if="error">
        <div ng-bind="error"></div>
    </div>
    <div  class="table-bg">
        <table class="table table-striped table-hover" ng-init="initList(1)">
            <tr>
                <td>序号</td>
                <td>用户名</td>
                <td>真实姓名</td>
                <td>注册时间</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td>{{item.nickname}}</td>
                <td>{{item.realName}}</td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <button class="btn btn-raised btn-primary">操作</button>
                </td>
            </tr>
        </table>
        <div admin-pagination-directive page-list="pageList" init-list="initList"
             page-count="pageQuery.pageCount" index="index"></div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/userManagerService.js"></script>
<script>
            app.controller("userController",function($scope,userManagerService){
                $scope.nav.index=1;
                $scope.leftNav.index=1;
                $scope.index=1;
                $scope.error="";
                $scope.initList=function(index) {
                    $scope.index = index;
                    userManagerService.initList(index)
                            .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.error="";
                            $scope.list = response.data.list;
                            $scope.pageQuery=response.data.pageQuery;
                            $scope.pageList=[];
                            for (var i=1;i<$scope.pageQuery.pageCount+1;i++){
                                $scope.pageList.push(i);
                            }
                        }else{
                            $scope.error=response.msg;
                        }

                    }).error(function(response,status,headers,cfg){
                        $scope.error="请求失败";
                    })
                }
            })
</script>
