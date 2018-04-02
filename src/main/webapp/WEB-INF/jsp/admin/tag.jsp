<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/8.0008
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-controller="tagController">
    <div  class="table-bg">
        <div class="alert alert-danger" ng-if="error">
            {{error}}
        </div>
        <table class="table table-striped table-hover" ng-init="initList(index)">
            <tr>
                <td>序号</td>
                <td>关键词</td>
                <td>关注指数</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td>{{item.name}}</td>
                <td>{{item.count}}</td>
            </tr>
        </table>
        <div admin-pagination-directive page-list="pageList" init-list="initList" page-count="pageQuery.pageCount" index="index"></div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/tagService.js"></script>
<script>
            app.controller("tagController",function($scope,tagService){
                $scope.nav.index=2;
                $scope.leftNav.index=2;
                $scope.error="";
                $scope.index=1;
                $scope.pageQuery={
                    pageCount:1
                }
                $scope.initList=function(index) {
                    if(index<=0){
                        return;
                    }
                    if(index>$scope.pageQuery.pageCount){
                        return;
                    }
                    $scope.index = index;
                    tagService.initList(index)
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
