<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/13.0013
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div  ng-controller="emailSettingEditController">
<!--编辑部分 start-->
    <div class="" style="margin-bottom: 12px">
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="hostName">
                    服务器地址
                </label>
            </div>
            <div class="col-md-4">
                <input id="hostName" type="text" ng-model="emailSetting.hostName"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入邮件服务器SMTP协议下的地址</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="smtpPort">
                    服务器端口
                </label>
            </div>
            <div class="col-md-4">
                <input id="smtpPort" type="text" ng-model="emailSetting.smtpPort"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入邮件服务器的端口号</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="authenticationName">
                    邮箱
                </label>
            </div>
            <div class="col-md-4">
                <input id="authenticationName" type="text" ng-model="emailSetting.authenticationName"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入发送邮件时使用的邮箱</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="authenticationPassword">
                    邮箱密码
                </label>
            </div>
            <div class="col-md-4">
                <input id="authenticationPassword" type="text" ng-model="emailSetting.authenticationPassword"  class="form-control">
        </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入发送邮件时使用邮箱的密码</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="charset">
                    编码方式
                </label>
            </div>
            <div class="col-md-4">
                <input id="charset" type="text" ng-model="emailSetting.charset"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入发送邮件时使用编码方式.(如：UTF-8)</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="sendPerson">
                    发送人
                </label>
            </div>
            <div class="col-md-4">
                <input id="sendPerson" type="text" ng-model="emailSetting.sendPerson"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入发送邮件时使用称呼</div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <div class="input-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"  ng-model="emailSetting.use"/> 默认邮箱
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <button class="btn btn-raised btn-primary" ng-click="edit(emailSetting)">保存</button>
                <button class="btn btn-raised btn-default" ng-click="reset()">重置</button>
            </div>
        </div>
        <div class="alert alert-danger" ng-if="error">
            <div ng-bind="error"></div>
        </div>
    </div>
<!--编辑部分 end-->
<!--列表部分 start-->
    <div class="">
        <table class="table table-striped table-hover" ng-init="initList(1)">
            <tr>
                <td>序号</td>
                <td>服务器地址</td>
                <td>服务器端口</td>
                <td>邮箱</td>
                <td>邮箱密码</td>
                <td>编码方式</td>
                <td>发送人</td>
                <td>默认</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="item in list">
                <td>{{item.id}}</td>
                <td>{{item.hostName}}</td>
                <td>{{item.smtpPort}}</td>
                <td>{{item.authenticationName}}</td>
                <td>{{item.authenticationPassword}}</td>
                <td>{{item.charset}}</td>
                <td>{{item.sendPerson}}</td>
                <td>{{item.use?'是':'否'}}</td>
                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                <td>
                    <button class="btn btn-raised btn-success btn-xs" ng-click="setDefault(item)">设为默认</button>
                    <button class="btn btn-raised btn-primary btn-xs" ng-click="gotoEdit(item)"><span class="glyphicon glyphicon-pencil"></span></button>
                </td>
            </tr>
        </table>
    </div>
<!--列表部分 end-->

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailSettingListService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailSettingEditService.js"></script>
<script>
    app.controller("emailSettingEditController",function($scope,emailSettingListService,emailSettingEditService){
        $scope.nav.index=4;
        $scope.leftNav.index=3;
        $scope.error="";
        $scope.emailSetting={};
        //初始化列表 start
        $scope.initList=function(index) {
            emailSettingListService.getList({
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
        //编辑 start
        $scope.edit=function(emailSetting){
            emailSettingEditService.edit(emailSetting)
                    .success(function(response,status,headers,cfg){
                if(response.success){
                    $scope.error="";
                    $scope.emailSetting={};
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

            })
        }
        //编辑 end
        $scope.gotoEdit=function(emailSetting){
            $scope.emailSetting = angular.copy(emailSetting);
        }
        $scope.setDefault=function(emailSetting){
            emailSetting.use=true;
            $scope.edit(emailSetting);
        }
        $scope.reset=function(){
            $scope.emailSetting={};
        }
    })
</script>
