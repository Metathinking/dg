<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/18.0018
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="recycleBinListController" ng-init="initList(${index})">
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
                <td><a href="${pageContext.request.contextPath}/article/{{item.id}}.html" target="_blank">{{item.title}}</a> </td>
                <td>{{item.tag}}</td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <button class="btn btn-raised btn-primary btn-sm" ng-click="restore(item,$index)">还原</button>
                    <button class="btn btn-raised btn-default btn-sm" ng-click="selectArticle(item,$index)" data-toggle="modal" data-target="#modal">彻底删除</button>
                </td>
            </tr>
        </table>
        <div front-pagination-directive page-list="pageList" link="/user/recycleBinArticle/list.html" page-count="pageQuery.pageCount" index="index"></div>
    </div>
    <div class="alert alert-info" ng-if="list.length==0">
        回收站是空的
    </div>
    <div class="alert alert-info" ng-if="info">
        {{info}}
    </div>
    <div class="alert alert-danger" ng-if="error">
        {{error}}
    </div>

    <!--modal start-->
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="detailHeadLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">删除</h4>
                </div>
                <div class="modal-body">
                    <p>这次再删掉就真的没有了!!!</p>
                    <div class="row">
                        <div class="col-12-xs text-center">
                            <button class="btn btn-raised btn-success btn-md" ng-click="delete(item)" data-dismiss="modal">删除</button>
                            <button class="btn btn-raised btn-danger btn-md" data-dismiss="modal" >取消</button>
                        </div>
                    </div>
                </div>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!--modal end-->
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/recycleBinArticleService.js"></script>
<script>
    app.controller('recycleBinListController', function ($scope, recycleBinArticleService) {
        $scope.nav.index=4;
        $scope.rightNav.index=8;
        //初始化列表 start
        $scope.initList = function (_index) {
            $scope.index = _index;
            var data = {
                index: _index
            }
            recycleBinArticleService.initList(data)
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

        $scope.selectArticle = function(_article,_index){
            $scope.article = _article;
            $scope.articleIndex=_index;
        }

        //彻底删除 start
        $scope.delete = function(){
            recycleBinArticleService.delete($scope.article.id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error="";
                            $scope.list.splice($scope.articleIndex,1);
                            $scope.article=null;
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //彻底删除 end

        //还原 start
        $scope.restore = function(_article,_index){
            recycleBinArticleService.restore(_article.id)
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
        //还原 end
    })
</script>
