<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/15.0015
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="siteInfoController" ng-init="find()" >
    <div class="" style="margin-bottom: 12px">
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="name">
                    网站名称
                </label>
            </div>
            <div class="col-md-8">
                <input id="name" type="text" ng-model="siteInfo.name"  class="form-control">
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="keywords">
                    Meta Keywords
                </label>
            </div>
            <div class="col-md-8">
                <input id="keywords" type="text" ng-model="siteInfo.keywords"  class="form-control">
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="url">
                    服务器地址
                </label>
            </div>
            <div class="col-md-8">
                <input id="url" type="text" ng-model="siteInfo.url"  class="form-control">
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="description">
                    Meta Description
                </label>
            </div>
            <div class="col-md-8">
                <textarea id="description" type="text" ng-model="siteInfo.description"  class="form-control" style="height: 200px;">
                </textarea>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="footer">
                    页脚
                </label>
            </div>
            <div class="col-md-8">
                <input id="footer" type="text" ng-model="siteInfo.footer"  class="form-control">
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="icon">
                    网站图标
                </label>
            </div>
            <div class="col-md-8">
                <input id="icon" type="text" ng-model="siteInfo.icon"  class="form-control" readonly/>
            </div>
        </div>
        <div ng-if="siteInfo.icon" class="text-center">
            <img ng-src="{{siteInfo.icon}}" style="width: 100px;"/>
        </div>
        <!--图片上传 start-->
        <div class="form-group ">
            <label>添加图片</label>(1、图片大小不能超过2M；2、支持格式：.jpg .gif .png .bmp)
            <div class="panel panel-default" ng-controller="imageController">
                <div class="panel-body" nv-file-drop="" uploader="imageController.uploader" filters="queueLimit, customFilter">
                    <input type="file" nv-file-select="" uploader="imageController.uploader"/>
                    <ol>
                        <li ng-repeat="item in imageController.images">{{item}}
                            <button class="btn btn-raised btn-sm btn-primary" ng-click="selectImage(item)">选择</button>
                        </li>
                    </ol>
                    <div class="alert alert-danger" ng-if="imageError">
                        {{imageError}}
                    </div>
                    <table class="table" ng-if="imageController.uploader.queue.length>0">
                        <thead>
                        <tr>
                            <th width="50%">图片</th>
                            <th ng-show="imageController.uploader.isHTML5">大小</th>
                            <th ng-show="imageController.uploader.isHTML5">进度</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in imageController.uploader.queue">
                            <td><strong>{{ item.file.name }}</strong></td>
                            <td ng-show="imageController.uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }} MB</td>
                            <td ng-show="imageController.uploader.isHTML5">
                                <div class="progress" style="margin-bottom: 0;">
                                    <div class="progress-bar" role="progressbar"
                                         ng-style="{ 'width': item.progress + '%' }"></div>
                                </div>
                            </td>
                            <td class="text-center">
                                <span ng-show="item.isSuccess">成功</span>
                                <span ng-show="item.isError">失败</span>
                            </td>
                            <td nowrap>
                                <button type="button" class="btn btn-success btn-xs" ng-click="item.upload()"
                                        ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                    上传
                                </button>
                                <button type="button" class="btn btn-danger btn-xs" ng-click="item.remove()">
                                    移除
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!--图片上传 end-->
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" >
                    上次更新时间
                </label>
            </div>
            <div class="col-md-8">
                <div class="control-label pull-left" ng-bind="siteInfo.time|date:'yyyy-MM-dd HH:mm:ss'"></div>
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
<script charset="utf-8" src="${pageContext.request.contextPath}/res/js/controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/siteInfoService.js"></script>
<script>
    app.controller('siteInfoController',function($scope,siteInfoService){
        $scope.nav.index=5;
        $scope.leftNav.index=1;
        $scope.imageController={
            images:[],
            uploader:{}
        };
        $scope.find = function(){
            siteInfoService.find()
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.error='';
                            $scope.siteInfo = response.data;
                            $scope.imageController.images=[];
                            $scope.imageController.uploader.queue=[];
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        $scope.save = function(){
            siteInfoService.save($scope.siteInfo)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.siteInfo=response.data;
                            $scope.error="";
                            $scope.info=response.msg;
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }

        //选择图片
        $scope.selectImage = function (_imageSrc) {
            $scope.siteInfo.icon = _imageSrc;
        }
        //选择图片

    })
</script>
