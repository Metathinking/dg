<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/14.0014
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script charset="utf-8" src="${pageContext.request.contextPath}/res/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons']
        });
    });
</script>

<div ng-controller="emailSendController" ng-init="getDefaultEmailSetting()">
    <div class="" style="margin-bottom: 12px">
        <div class="row form-group form-horizontal" ng-init="findEditEmailById(${id})">
            <div class="col-md-2">
                <label class="control-label pull-right" for="hostName">
                    服务器地址
                </label>
            </div>
            <div class="col-md-4">
                <input id="hostName" type="text" ng-model="emailRecord.hostName"  class="form-control">
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
                <input id="smtpPort" type="text" ng-model="emailRecord.smtpPort"  class="form-control">
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
                <input id="authenticationName" type="text" ng-model="emailRecord.authenticationName"  class="form-control">
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
                <input id="authenticationPassword" type="text" ng-model="emailRecord.authenticationPassword"  class="form-control">
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
                <input id="charset" type="text" ng-model="emailRecord.charset"  class="form-control">
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
                <input id="sendPerson" type="text" ng-model="emailRecord.sendPerson"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入发送邮件时使用称呼</div>
            </div>
        </div>

        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="toEmail">
                    收件邮箱
                </label>
            </div>
            <div class="col-md-4">
                <input id="toEmail" type="text" ng-model="emailRecord.toEmail"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入收件邮箱地址</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="subject">
                    主题
                </label>
            </div>
            <div class="col-md-4">
                <input id="subject" type="text" ng-model="emailRecord.subject"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请输入邮件主题</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" >
                    内容
                </label>
            </div>
            <div class="col-md-8">
                  <textarea id="editor_id" name="content" style="width:100%;height:200px;" ng-model="content">
                                        &lt;strong&gt;HTML内容&lt;/strong&gt;
                  </textarea>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <button type="button" class="btn btn-raised btn-default" data-toggle="modal" data-target="#templateModal">内容模板</button>
                <button type="button" class="btn btn-raised btn-default" data-toggle="modal" data-target="#modal">选择发送邮箱</button>
                <button type="button" class="btn btn-raised btn-primary" ng-click="send()">发送</button>
            </div>
        </div>
        <div class="alert alert-danger" ng-if="error">
            <div ng-bind="error"></div>
        </div>
        <div class="alert alert-info" ng-if="info">
            <div ng-bind="info"></div>
        </div>
    </div>

    <!--邮箱模态框 start-->
    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="headLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="headLabel">
                        选择邮箱设置
                    </h4>
                </div>
                <div class="modal-body" ng-init="initList(1)">
                    <table class="table table-striped table-hover" ng-if="list">
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
                                <button class="btn btn-raised btn-primary"   data-dismiss="modal" ng-click="select(item)">选择</button>
                            </td>
                        </tr>
                    </table>
                    <div class="alert alert-danger" ng-if="list.length==0">
                        请添加邮箱设置
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-raised btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--邮箱模态框 end-->

    <!--内容模板modal start-->
    <div class="modal fade" id="templateModal" tabindex="-1" role="dialog" aria-labelledby="templateHeadLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="templateHeadLabel">
                        选择邮箱设置
                    </h4>
                </div>
                <div class="modal-body" ng-init="initTemplateList(1)">
                    <table class="table table-striped table-hover" >
                        <tr>
                            <td>序号</td>
                            <td>类型</td>
                            <td>描述</td>
                            <td>默认</td>
                            <td>发送时间</td>
                            <td>操作</td>
                        </tr>
                        <tr ng-repeat="item in templateList">
                            <td>{{item.id}}</td>
                            <td>{{item.type}}</td>
                            <td>{{item.description}}</td>
                            <td>{{item.use?'是':'否'}}</td>
                            <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                            <td>
                                <button class="btn btn-raised btn-info " ng-click="showDetail(item)" data-toggle="modal" data-target="#detailModal">查看</button>
                                <button class="btn btn-raised btn-primary" ng-click="selectTemplate(item)" data-dismiss="modal">选择</button>
                            </td>
                        </tr>
                    </table>
                    <div class="alert alert-danger" ng-if="templateList.length==0">
                        请添加模板信息
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-raised btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--内容模板modal end-->

    <!--详情modal start -->
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailHeadLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="detailHeadLabel">
                        邮箱模板
                    </h4>
                </div>
                <div class="modal-body" >
                    类型:<div ng-bind="emailMsgTemplate.type"></div>
                    <div class="panel panel-default" id="content">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-raised btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--详情modal end  -->
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailSettingListService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailSendService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/templateListService.js"></script>
<script>
    app.controller('emailSendController',function($scope,
                                                  emailSendService,
                                                  emailSettingListService,
                                                  templateListService){
        $scope.nav.index=4;
        $scope.leftNav.index=1;
        $scope.emailRecord={};
        //获取默认邮箱设置 start
        $scope.getDefaultEmailSetting= function(){
            emailSendService.getDefault()
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.error="";
                            var emailSetting=response.data;
                           $scope.emailRecord = emailSendService.toRecord(emailSetting);
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        //获取默认邮箱设置 end

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

        //选择邮箱设置 start
        $scope.select = function(emailSetting){
            var record = $scope.emailRecord;
            $scope.emailRecord = emailSendService.toRecord(emailSetting);
            $scope.emailRecord.toEmail = record.toEmail;
            $scope.emailRecord.subject = record.subject;
            $scope.emailRecord.htmlMsg= record.htmlMsg;
        }
        //选择邮箱设置 end

        $scope.send = function(){
            window.editor.sync();
            $scope.emailRecord.htmlMsg= document.getElementById('editor_id').value;
            emailSendService.send($scope.emailRecord)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.error="";
                           $scope.info=response.msg;
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){

            })
        }
        $scope.findEditEmailById = function(_id){
            if(_id==-1){
                return;
            }
            emailSendService.findEditEmailById(_id)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.emailRecord=response.data;
                            window.editor.html($scope.emailRecord.htmlMsg);
                            $scope.error="";
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }

        //初始化模板列表 start
        $scope.initTemplateList=function(index) {
            templateListService.getList({
                index:index
            }).success(function(response,status,headers,cfg){
                if(response.success){
                    $scope.error="";
                    $scope.templateList = response.data.list;
                    $scope.pageQuery=response.data.pageQuery;
                    $scope.templatePageList=[];
                    for (var i=1;i<$scope.pageQuery.pageCount+1;i++){
                        $scope.templatePageList.push(i);
                    }
                }else{
                    $scope.error=response.msg;
                }
            }).error(function(response,status,headers,cfg){
                $scope.error="请求失败";
            })
        }
        //初始化模板列表 end

        //模板详情 start
        $scope.showDetail = function(_emailMsgTemplate){
            $scope.emailMsgTemplate = _emailMsgTemplate;
            document.getElementById("content").innerHTML=_emailMsgTemplate.content;
        }
        //模板详情 end

        $scope.selectTemplate = function(_emailMsgTemplate){
            window.editor.html(_emailMsgTemplate.content);
        }
    })
</script>