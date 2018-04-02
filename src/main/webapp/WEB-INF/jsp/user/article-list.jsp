<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="articleListController" ng-init="initList(${index})">
    <div ng-if="list.length>0">
        <table class="table table-striped table-hover">
            <tr>
                <td>序号</td>
                <td>标题</td>
                <td>标签</td>
                <td>时间</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td><a href="${pageContext.request.contextPath}/article/{{item.id}}.html" target="_blank">{{item.title}}</a> </td>
                <td>{{item.tag}}</td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>{{item.open?'发表':'草稿'}}</td>
                <td>
                    <button class="btn btn-raised btn-info btn-sm btn-option" ng-if="!item.open" ng-click="changeOpen(item,true)">发表</button>
                    <button class="btn  btn-raised btn-info btn-sm btn-option" ng-if="item.open" ng-click="changeOpen(item,false)" >关闭</button>
                    <button class="btn btn-raised btn-primary btn-sm btn-option" ng-click="gotoEdit(item.id)" ><span class="glyphicon glyphicon-pencil"></span></button>
                    <button class="btn btn-raised btn-danger btn-sm btn-option" ng-click="delete(item,$index)" ><span class="glyphicon glyphicon-trash"></span></button>
                </td>

            </tr>
        </table>

        <div front-pagination-directive page-list="pageList" link="/user/articleList.html" page-count="pageQuery.pageCount" index="index"></div>
    </div>
    <div class="alert alert-info" ng-if="list.length==0">
        你还没有文章需要管理
    </div>
        <div class="alert alert-info" ng-if="info">
            {{info}}
        </div>
        <div class="alert alert-danger" ng-if="error">
            {{error}}
        </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/articleService.js"></script>
<script>
    app.controller('articleListController', function ($scope, articleService) {
        $scope.nav.index=4;
        $scope.rightNav.index=1;
        //初始化列表 start
        $scope.initList = function (_index) {
            $scope.index = _index;
            var data = {
                index: _index
            }
            articleService.initList(data)
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
        //更新文章发布状态 start
        $scope.changeOpen = function(_article,_open){
            if(_article.open==_open){
                return;
            }
            _article.open=_open;
            var data={
                id:_article.id,
                open:_open
            }
            articleService.changeOpen(data)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error="";
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //更新文章发布状态 end

        $scope.gotoEdit = function(_id){
            window.location.href="${pageContext.request.contextPath}/user/articleEdit.html?id="+_id;
        }

        $scope.delete = function(_article,_index){

            articleService.delete(_article.id)
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
    })
</script>