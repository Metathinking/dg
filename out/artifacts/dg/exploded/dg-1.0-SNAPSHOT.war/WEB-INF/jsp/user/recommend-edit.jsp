<%--
Created by IntelliJ IDEA.
User: luyilaosan1
Date: 2016/7/1.0001
Time: 19:07
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="recommendEditController" ng-init="getRecommend(${id})">
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="description">
                描述
            </label>
        </div>
        <div class="col-md-8">
            <input id="description" type="text" ng-model="recommend.description" class="form-control">
        </div>
    </div>
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="shopUrl">
                原地址
            </label>
        </div>
        <div class="col-md-8">
            <textarea id="shopUrl" type="text" ng-model="recommend.shopUrl" style="width: 100%;height: 200px">
            </textarea>
        </div>
    </div>
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="recommendUrl">
                推荐地址
            </label>
        </div>
        <div class="col-md-8">
            <textarea id="recommendUrl" type="text" ng-model="recommend.recommendUrl"
                      style="width: 100%;height: 200px"></textarea>
        </div>
    </div>
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="image">
                图片
            </label>
        </div>
        <div class="col-md-8">
            <input id="image" type="text" ng-model="recommend.image" class="form-control" readonly/>
        </div>
    </div>
    <div ng-if="recommend.image" class="text-center" >
        <img ng-src="{{recommend.image}}" style="width: 200px;"/>
    </div>
    <!--图片上传 start-->
    <div class="form-group " style="margin-right: 80px;margin-left: 80px">
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
                            <button type="button" class="btn btn-raised btn-success btn-xs" ng-click="item.upload()"
                                    ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                上传
                            </button>
                            <button type="button" class="btn btn-raised btn-danger btn-xs" ng-click="item.remove()">
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
    <div class="form-group text-center">
        <button type="button" class="btn btn-raised btn-primary" ng-click="edit()">保存</button>
        <button type="button" class="btn btn-raised btn-default" ng-click="reset()">重置</button>
    </div>
    <div class="alert alert-danger" ng-if="error">
        {{error}}
    </div>
    <div class="alert alert-info" ng-if="info">
        {{info}}
    </div>
</div>
<script charset="utf-8" src="${pageContext.request.contextPath}/res/js/controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/recommendService.js"></script>
<script>
    app.controller("recommendEditController", function ($scope, recommendService) {
        $scope.nav.index = 4;
        $scope.rightNav.index = 4;
        $scope.recommend = {
            image: '',
            description: '',
            shopUrl: '',
            recommendUrl: ''
        };
        $scope.imageController={
            images:[],
            uploader:{}
        };
//提交编辑 start
        $scope.edit = function () {
            recommendService.edit($scope.recommend)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.info = response.msg;
                            $scope.reset();
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
//提交编辑 end

//获取要编辑的信息 start
        $scope.getRecommend = function (_id) {
            if (_id == -1) {
                return;
            }
            recommendService.getRecommend(_id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
                            $scope.recommend = response.data;
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
//获取要编辑的信息 end

        $scope.reset = function () {
            $scope.recommend = {
                image: '',
                description: '',
                shopUrl: '',
                recommendUrl: ''
            };
            $scope.imageController.images=[];
            $scope.imageController.uploader.queue=[];
        }

        //选择推广使用的图片
        $scope.selectImage = function (_imageSrc) {
            $scope.recommend.image = _imageSrc;
        }
    })
</script>
