<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="panel panel-default " ng-controller="forgetPasswordSendController">

    <div class="panel-body text-center">
        <div class="passport-forget">
            <ul class="passport-step">
                <li class="first now"><i class="disc">1</i>
                    <p class="text">输入邮箱地址</p>
                </li>
                <li ng-class="{now:hasSend}"><i class="disc">2</i><span class="strip"></span>
                    <p class="text">邮箱验证</p>
                </li>
                <li class=""><i class="disc">3</i><span class="strip"></span>
                    <p class="text">重置密码</p>
                </li>
            </ul>
            <div class="passport-form passport-form-email" ng-if="!hasSend">
                <div class="form-item">
                    <div class="form-cont">
                        <input type="text" ng-model="data.email" class="form-control" tabindex="1" placeholder="请输入注册时使用的邮箱地址" />
                    </div>
                </div>
                <div class="form-item form-submit">
                    <div class="form-cont">
                        <button type="button" class="btn btn-primary btn-lg btn-block" ng-click="sendEmail()">找回密码</button>
                    </div>
                </div>
            </div>
            <div class="passport-form passport-form-email" ng-if="hasSend">
                <div class="form-item form-text">
                    <p><div ng-bind="backMsg"></div></p>
                </div>
            </div>
            <div class="passport-form passport-form-email">
                <div class="form-item">
                    <div class="alert alert-danger" ng-if="error">
                        <div ng-bind="error"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/forgetPasswordSendService.js"></script>
<script>
    app.controller("forgetPasswordSendController", function ($scope, forgetPasswordSendService) {
        $scope.data={
            email:""
        }
        $scope.hasSend=false;
        $scope.sendEmail=function(){
            $scope.error="";
            $scope.info="邮件发送中..."
            if($scope.data.email.length==0){
                $scope.error="请输入注册邮箱";
                return;
            }
            if(!Util.checkEmail($scope.data.email)){
                $scope.error="邮箱格式不正确";
                return;
            }
            forgetPasswordSendService.sendEmail($scope.data.email)
                    .success(function(response,status,headers,cfg){
                        if (response.success) {
                            $scope.hasSend=true;
                            $scope.error = "";
                            $scope.backMsg = response.msg;
                        } else {
                            $scope.hasSend=false;
                            $scope.backMsg = "";
                            $scope.error = response.msg;
                        }
                    }).error(function(response,status,headers,cfg) {
                $scope.hasSend=false;
                $scope.backMsg = "";
                $scope.error = "连接失败";
            })
        }

    })
</script>