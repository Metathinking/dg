<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/15.0015
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div ng-controller="siteSettingController" ng-init="find()" >
    <div class="" style="margin-bottom: 12px">
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="mainArticleCount">
                    首页文章数量
                </label>
            </div>
            <div class="col-md-4">
                <input id="mainArticleCount" type="text" ng-model="siteSetting.mainArticleCount"  class="form-control">
            </div>
            <div class="col-md-4">
                <div class="control-label pull-left">最少12条</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="articleCommentCount">
                    文章下显示评论数量
                </label>
            </div>
            <div class="col-md-4">
                <input id="articleCommentCount" type="text" ng-model="siteSetting.articleCommentCount"  class="form-control">
            </div>
            <div class="col-md-4">
                <div class="control-label pull-left">最少12条</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="articlePageSize">
                    文章分页每页数量
                </label>
            </div>
            <div class="col-md-4">
                <input id="articlePageSize" type="text" ng-model="siteSetting.articlePageSize"  class="form-control">
            </div>
            <div class="col-md-4">
                <div class="control-label pull-left">最少20条</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right" for="commentPageSize">
                    评论分页每页数量
                </label>
            </div>
            <div class="col-md-4">
                <input id="commentPageSize" type="text" ng-model="siteSetting.commentPageSize"  class="form-control">
            </div>
            <div class="col-md-4">
                <div class="control-label pull-left ">最少30条</div>
            </div>
        </div>
        <div class="row form-group form-horizontal">
            <div class="col-md-2">
                <label class="control-label pull-right">
                    上次更新时间
                </label>
            </div>
            <div class="col-md-4">
                <div class="control-label pull-left" ng-bind="siteSetting.time|date:'yyyy-MM-dd HH:mm:ss'"></div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/siteSettingService.js"></script>
<script>
    app.controller('siteSettingController',function($scope,siteSettingService){
        $scope.nav.index=5;
        $scope.leftNav.index=2;

        //获取 网站设置信息 start
        $scope.find = function(){
            siteSettingService.find()
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.siteSetting=response.data;
                            $scope.error="";
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        //获取网站设置信息 end

        //保存 start
        $scope.save = function(){
            siteSettingService.save($scope.siteSetting)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            $scope.siteSetting=response.data;
                            $scope.error="";
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
        //保存 end
    })
</script>
