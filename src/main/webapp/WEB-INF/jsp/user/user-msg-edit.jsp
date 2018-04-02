<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="" ng-controller="userMsgEditController" ng-init="find()">
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="icon">
                头像
            </label>
        </div>
        <div class="col-md-8">
            <input id="icon" type="text" ng-model="userMsg.icon" class="form-control" readonly/>
        </div>
    </div>
    <div ng-if="userMsg.icon" class="text-center">
        <img ng-src="{{userMsg.icon}}" style="width: 100px;border: 1px dashed #2e353d;"/>
    </div>
    <!--图片上传 start-->
    <div class="form-group " style="margin-right: 80px;margin-left: 80px">
        添加图片(1、图片大小不能超过2M；2、支持格式：.jpg .gif .png .bmp)
        <div class="panel panel-default" ng-controller="imageController">
            <div class="panel-body" nv-file-drop="" uploader="imageController.uploader"
                 filters="queueLimit, customFilter">
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
                        <td ng-show="imageController.uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }}
                            MB
                        </td>
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
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right">
                个人简介
            </label>
        </div>
        <div class="col-md-8">
            <textarea type="text" ng-model="userMsg.introduction"
                      style="width:100%;height: 200px;"></textarea>
        </div>
    </div>
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right">
                性别
            </label>
        </div>
        <div class="col-md-2">
            <div class="radio">
                <label>
                    <input type="radio" value="男" name="sex" ng-model="userMsg.sex">男
                </label>
            </div>
        </div>
        <div class="col-md-2">
            <div class="radio">
                <label>
                    <input type="radio" value="女" name="sex" ng-model="userMsg.sex">女
                </label>
            </div>
            <%--<input type="radio" value="男" name="sex" ng-model="userMsg.sex"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<input type="radio" value="女" name="sex" ng-model="userMsg.sex"/>女--%>
        </div>
    </div>
    <div class="row form-group">
        <div class="col-md-2">
            <label class="control-label pull-right" for="height">
                身高
            </label>
        </div>
        <div class="col-md-2">
            <input id="height" type="number" ng-model="userMsg.height" class="form-control"/>
        </div>
        <div class="col-md-2">
            <label class="control-label pull-left">
                cm(厘米)
            </label>
        </div>
    </div>
    <div class="row form-group form-horizontal">
        <div class="col-md-2">
            <label class="control-label pull-right" for="weight">
                体重
            </label>
        </div>
        <div class="col-md-2">
            <input id="weight" type="number" ng-model="userMsg.weight" class="form-control"/>
        </div>
        <div class="col-md-2">
            <label class="control-label pull-left">
                kg(公斤)
            </label>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            三围
        </div>
        <div class="panel-body">
            <div class="row form-group form-horizontal">
                <div class="col-md-2">
                    <label class="control-label pull-right" for="bust">
                        胸围
                    </label>
                </div>
                <div class="col-md-2">
                    <input id="bust" type="number" ng-model="userMsg.bust" class="form-control"/>
                </div>
                <div class="col-md-2">
                    <label class="control-label pull-left">
                        cm(厘米)
                    </label>
                </div>
            </div>
            <div class="row form-group form-horizontal">
                <div class="col-md-2">
                    <label class="control-label pull-right" for="waist">
                        腰围
                    </label>
                </div>
                <div class="col-md-2">
                    <input id="waist" type="number" ng-model="userMsg.waist" class="form-control"/>
                </div>
                <div class="col-md-2">
                    <label class="control-label pull-left">
                        cm(厘米)
                    </label>
                </div>
            </div>
            <div class="row form-group form-horizontal">
                <div class="col-md-2">
                    <label class="control-label pull-right" for="hips">
                        臀围
                    </label>
                </div>
                <div class="col-md-2">
                    <input id="hips" type="number" ng-model="userMsg.hips" class="form-control"/>
                </div>
                <div class="col-md-2">
                    <label class="control-label pull-left">
                        cm(厘米)
                    </label>
                </div>
            </div>
        </div>
    </div>

    <div class="alert alert-danger" ng-if="error">
        {{error}}
    </div>
    <div class="form-group text-center">
        <button class="btn btn-raised btn-primary" style="margin-left: 40px;margin-right: 40px" ng-click="save()">立即保存
        </button>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/controller.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/userMsgService.js"></script>
<script>
    app.controller("userMsgEditController", function ($scope, userMsgService) {
        $scope.nav.index = 4;
        $scope.rightNav.index = 9;
        $scope.imageController = {
            images: [],
            uploader: {}
        };
        //获取用户信息 start
        $scope.find = function () {
            userMsgService.find()
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.userMsg = response.data;
                            if ($scope.userMsg == null) {
                                $scope.userMsg = {};
                            }
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })

        }
        //获取用户信息 end
        //更新用户信息 start
        $scope.save = function () {

            userMsgService.save($scope.userMsg)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = "";
                            alert(response.msg);
                            window.location.reload();
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
        //更新用户信息 end
        //选择头像 start
        $scope.selectImage = function (_image) {
            $scope.userMsg.icon = _image;
        }
        //选择头像 end
    })
</script>