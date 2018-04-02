<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/8.0008
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-controller="suggestionController">
    <div class="table-bg">
        <div class="alert alert-danger" ng-if="error">
            {{error}}
        </div>
        <table class="table table-striped table-hover" ng-init="initList(index)">
            <tr>
                <td>序号</td>
                <td>标题</td>
                <td>意见/建议</td>
                <td>联系方式</td>
                <td>状态</td>
                <td>ip</td>
                <td>ID</td>
                <td>时间</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td>{{item.title}}</td>
                <td>{{item.content}}</td>
                <td>{{item.contactWay}}</td>
                <td>{{item.handled?'已处理':'未处理'}}</td>
                <td>{{item.ip}}</td>
                <td>{{item.userId}}</td>
                <td>
                    {{item.time|date:'yyyy-MM-dd HH:mm:ss'}}
                </td>
                <td>
                    <button class="btn btn-raised btn-danger btn-sm" ng-click="delete(item,$index)" title="删除"><span class="glyphicon glyphicon-remove"></span></button>
                    <button ng-if="!item.handled" class="btn btn-raised btn-primary btn-sm" ng-click="handle(item)" title="处理完成"><span class="glyphicon glyphicon-ok"></span></button>
                </td>
            </tr>
        </table>
        <div admin-pagination-directive page-list="pageList" init-list="initList"
             page-count="pageQuery.pageCount" index="index"></div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/adminSuggestionService.js"></script>
<script>
    app.controller("suggestionController", function ($scope, adminSuggestionService) {
        $scope.nav.index=1;
        $scope.leftNav.index=3;
        $scope.error = "";
        $scope.index = 1;
        $scope.pageQuery = {
            pageCount: 1
        }
        //获取列表数据 start
        $scope.initList = function (index) {
            $scope.index = index;
            adminSuggestionService.initList(index)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
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
                $scope.error = "请求失败";
            })
        }
        //获取列表数据 end

        //处理反馈 start
        $scope.handle = function (_suggestion) {
            adminSuggestionService.handle(_suggestion.id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
                            _suggestion.handled=true;
                        } else {
                            $scope.error = response.msg;
                        }

                    }).error(function (response, status, headers, cfg) {
                         $scope.error = "请求失败";
                     })
        }
        //处理反馈 end

        //删除反馈 start
        $scope.delete = function(_suggestion,_index){
            adminSuggestionService.delete(_suggestion.id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
                            $scope.list.splice(_index,1);
                        } else {
                            $scope.error = response.msg;
                        }

                    }).error(function (response, status, headers, cfg) {
                $scope.error = "请求失败";
            })
        }
        //删除反馈 end
    })
</script>
