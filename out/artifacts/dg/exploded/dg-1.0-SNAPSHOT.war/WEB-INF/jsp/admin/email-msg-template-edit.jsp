<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/15.0015
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script charset="utf-8" src="${pageContext.request.contextPath}/res/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/res/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#content', {
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

<div ng-controller="templateEditController" ng-init="getTemplateTypes()" >
    <div class="" style="margin-bottom: 12px">
        <div class="row form-group form-horizontal"  ng-init="findTemplateById(${id})">
            <div class="col-md-2">
                <label class="control-label pull-right">
                    类型
                </label>
            </div>
            <div class="col-md-4">
                <select ng-model="emailMsgTemplate.type" ng-options="t.name as t.description for t in types" class="form-control">
                    <option value=""></option>
                </select>
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">请选择模板类型</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="subject">
                    主题
                </label>
            </div>
            <div class="col-md-4">
                <input id="subject" type="text" ng-model="emailMsgTemplate.subject"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">发送邮件使用的主题</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="description">
                    描述
                </label>
            </div>
            <div class="col-md-4">
                <input id="description" type="text" ng-model="emailMsgTemplate.description"  class="form-control">
            </div>
            <div class="col-md-4" >
                <div class="control-label pull-left">模板描述</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" >
                    内容
                </label>
            </div>
            <div class="col-md-8">
                  <textarea id="content" style="width:100%;height:200px;" >
                                        &lt;strong&gt;HTML内容&lt;/strong&gt;
                  </textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <div class="input-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"  ng-model="emailMsgTemplate.use"/> 默认模板
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <button class="btn btn-raised btn-primary" ng-click="save()">保存</button>
            </div>
        </div>
        <div class="alert alert-danger" ng-if="error">
            <div ng-bind="error"></div>
        </div>
        <div class="alert alert-info" ng-if="info">
            <div ng-bind="info"></div>
        </div>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/emailMsgTemplateEditService.js"></script>
<script>
    app.controller('templateEditController',function($scope,emailMsgTemplateEditService){
        $scope.nav.index=4;
        $scope.leftNav.index=4;
        $scope.emailMsgTemplate={};
        $scope.types=[];
        $scope.getTemplateTypes = function(){
            emailMsgTemplateEditService.getTemplateTypes()
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.types=response.data;
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        $scope.save = function(){
            window.editor.sync();
            $scope.emailMsgTemplate.content= document.getElementById('content').value;
            emailMsgTemplateEditService.save($scope.emailMsgTemplate)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.emailMsgTemplate={};
                            $scope.error="";
                            $scope.info=response.msg;
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        $scope.findTemplateById = function(_id){
            if(_id==-1){
                return
            }
            emailMsgTemplateEditService.findById(_id)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.emailMsgTemplate=response.data;
                            window.editor.html($scope.emailMsgTemplate.content);
                            $scope.error="";
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
    })
</script>
