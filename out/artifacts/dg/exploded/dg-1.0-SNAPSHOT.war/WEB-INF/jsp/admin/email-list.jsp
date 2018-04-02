<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/14.0014
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="emailRecordListController">
    <table class="table table-striped table-hover" ng-init="initList(index)">
        <tr>
            <td>序号</td>
            <td>主题</td>
            <td>发件邮箱</td>
            <td>发送人</td>
            <td>收件人</td>
            <td>发送时间</td>
            <td>操作</td>
        </tr>
        <tr ng-repeat="item in list">
            <td>{{item.id}}</td>
            <td>{{item.subject}}</td>
            <td>{{item.authenticationName}}</td>
            <td>{{item.sendPerson}}</td>
            <td>{{item.toEmail}}</td>
            <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
            <td>
                <button class="btn btn-raised btn-primary btn-sm" ng-click="gotoEdit(item)" ><span class="glyphicon glyphicon-pencil"></span></button>
                <button class="btn btn-raised btn-success btn-sm" ng-click="showDetail(item)" data-toggle="modal" data-target="#modal"><span class="glyphicon glyphicon-eye-open"></span></button>
            </td>
        </tr>
    </table>
    <div admin-pagination-directive page-list="pageList" init-list="initList" page-count="pageQuery.pageCount" index="index"></div>
    <!--modal start -->
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="headLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="headLabel">
                        邮件详情
                    </h4>
                </div>
                <div class="modal-body" >
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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailRecordListService.js"></script>
<script>
    app.controller('emailRecordListController',function($scope,emailRecordListService){
        $scope.nav.index=4;
        $scope.leftNav.index=2;

        $scope.index=1;
        //初始化列表 start
        $scope.initList=function(index) {
            $scope.index=index;
            emailRecordListService.getList({
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

        $scope.showDetail = function(_emailRecord){
            document.getElementById("content").innerHTML=_emailRecord.htmlMsg;
        }

        $scope.gotoEdit = function(_emailRecord){
            window.location.href="${pageContext.request.contextPath}/admin/email/send.html?id="+_emailRecord.id;
        }
    })
</script>
