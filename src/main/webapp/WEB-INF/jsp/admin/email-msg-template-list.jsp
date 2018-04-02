<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/15.0015
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="templateListController">
    <table class="table table-striped table-hover" ng-init="initList(1)">
        <tr>
            <td>序号</td>
            <td>类型</td>
            <td>描述</td>
            <td>默认</td>
            <td>发送时间</td>
            <td>操作</td>
        </tr>
        <tr ng-repeat="item in list">
            <td>{{item.id}}</td>
            <td>{{item.type}}</td>
            <td>{{item.description}}</td>
            <td>{{item.use?'是':'否'}}</td>
            <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
            <td>
                <button class="btn btn-raised btn-warning btn-sm" ng-click="showDetail(item)" data-toggle="modal" data-target="#modal" title="查看"><span class="glyphicon glyphicon-eye-open"></span></button>
                <button class="btn btn-raised btn-primary btn-sm" ng-click="gotoEdit(item)" title="编辑"><span class="glyphicon glyphicon-pencil"></span></button>
                <button class="btn btn-raised btn-success btn-sm" ng-click="setDefault(item)" title="设为默认">设为默认</button>
            </td>
        </tr>
    </table>

    <!--modal start -->
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="headLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="headLabel">
                        邮箱模板
                    </h4>
                </div>
                <div class="modal-body" >
                   类型:<div ng-bind="emailMsgTemplate.type"></div>
                    <div class="panel panel-default" id="content">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--modal end  -->
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/templateListService.js"></script>
<script>
    app.controller('templateListController',function($scope,templateListService){
        $scope.nav.index=4;
        $scope.leftNav.index=5;

        //初始化列表 start
        $scope.initList=function(index) {
            templateListService.getList({
                index:index
            }).success(function(response,status,headers,cfg){
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
        //初始化列表 end

        $scope.showDetail = function(_emailMsgTemplate){
            $scope.emailMsgTemplate = _emailMsgTemplate;
            document.getElementById("content").innerHTML=_emailMsgTemplate.content;
        }

        $scope.gotoEdit = function(_emailMsgTemplate){
            window.location.href="${pageContext.request.contextPath}/admin/emailMsgTemplate/edit.html?id="+_emailMsgTemplate.id;
        }

        $scope.setDefault = function(_emailMsgTemplate){
            if(_emailMsgTemplate.use){
                return;
            }
            templateListService.setDefault(_emailMsgTemplate.id)
                    .success(function(response){
                        if(response.success){
                            $scope.initList($scope.pageQuery.index)
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(){
                $scope.error="请求失败";
            })
        }
    })
</script>
