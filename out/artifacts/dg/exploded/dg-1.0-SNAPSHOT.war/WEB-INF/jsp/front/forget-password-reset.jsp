<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="panel panel-default " ng-controller="forgetPasswordResetController">
    <div class="panel-body text-center">
        <div class="passport-forget">
            <ul class="passport-step">
                <li class="first past"><i class="disc">1</i>
                    <p class="text">输入邮箱地址</p>
                </li>
                <li class="past"><i class="disc">2</i><span class="strip"></span>
                    <p class="text">邮箱验证</p>
                </li>
                <li class="now"><i class="disc">3</i><span class="strip"></span>
                    <p class="text">重置密码</p>
                </li>
            </ul>
            <div class="passport-form passport-form-email" >
                <div class="form-item">
                    <div class="form-cont">
                        <p>邮箱地址：<em>${email}</em></p>
                    </div>
                </div>
                <div class="form-item">
                    <div class="form-cont">
                        <input type="password" ng-model="data.password" class="form-control" tabindex="1" placeholder="输入6~32位密码" />
                    </div>
                </div>
                <div class="form-item">
                    <div class="form-cont">
                        <input type="password" ng-model="data.rePassword" class="form-control" tabindex="1" placeholder="请再次输入新密码" />
                    </div>
                </div>
                <div class="form-item form-submit">
                    <div class="form-cont">
                        <button type="button" class="btn btn-primary btn-lg btn-block" ng-click="resetPassword('${email}')">确认</button>
                    </div>
                </div>
            </div>
            <div class="alert alert-danger" ng-if="error">
                <div ng-bind="error"></div>
            </div>
            <div class="alert alert-success" ng-if="info">
                <div  ng-bind="info"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/forgetPasswordResetService.js"></script>
<script>
    app.controller('forgetPasswordResetController',function($scope,forgetPasswordResetService){
        $scope.data={
            password:'',
            rePassword:''
        };
        $scope.resetPassword = function(_email){
            $scope.data.email=_email;
            forgetPasswordResetService.resetPassword($scope.data)
                    .success(function(response,status,headers,cfg){
                        if(response.success){
                            window.location.href="${pageContext.request.contextPath}/login.html";
                        }else{
                            $scope.error=response.msg;
                        }
                    }).error(function(response,status,headers,cfg){})
        }
    })
</script>