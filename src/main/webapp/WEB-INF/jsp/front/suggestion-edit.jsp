<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/7/3.0003
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="panel panel-default " ng-controller="suggestionController">
    <div class="panel-body text-center">
        <div class="passport-forget">
            <div class="suggestion-form" ng-if="!hasSend">
                <div class="form-item">
                    <div class="form-cont">
                        <input type="text" ng-model="suggestion.title" class="form-control" tabindex="1"
                               placeholder="标题"/>
                    </div>
                </div>
                <div class="form-item">
                    <div class="form-cont">
                        <textarea type="text" ng-model="suggestion.content" class="form-control" tabindex="1"
                                  placeholder="请输入您的宝贵建议或意见" style="height: 200px"></textarea>
                    </div>
                </div>
                <div class="form-item">
                    <div class="form-cont">
                        <input type="text" ng-model="suggestion.contactWay" class="form-control" tabindex="1"
                               placeholder="您的联系方式"/>
                    </div>
                </div>
                <div class="form-item form-submit">
                    <div class="form-cont">
                        <button type="button" class="btn btn-primary btn-lg btn-block" ng-click="submit()">提交</button>
                    </div>
                </div>
            </div>
            <div class="suggestion-form" ng-if="hasSend">
                <div class="form-item alert alert-success">
                    <p>
                    <div ng-bind="backMsg"></div>
                    </p>
                </div>
            </div>
            <div class="suggestion-form">
                <div class="form-item">
                    <div class="alert alert-danger" ng-if="error">
                        <div ng-bind="error"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/suggestionService.js"></script>
<script>
    app.controller('suggestionController', function ($scope, suggestionService) {
        $scope.suggestion = {};
        $scope.hasSend = false;
        $scope.submit = function () {
            suggestionService.submit($scope.suggestion)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.error = '';
                            $scope.backMsg = response.msg;
                            $scope.hasSend = true;
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.error = response;
            })
        }
    })
</script>
