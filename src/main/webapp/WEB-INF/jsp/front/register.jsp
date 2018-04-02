<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div  ng-controller="registerController">
    <div class="passport-wrapper ">
        <div class="text-center" style="margin-bottom: 12px">
            <a href="${pageContext.request.contextPath}/">
                <img class="head-logo" src="${pageContext.request.contextPath}/res/img/logo.png"/>
            </a>
        </div>
        <div class="passport-sign">
            <div class="row">
                <h3 class="heading-desc text-center" style="display:block">注册</h3>
                <div class="pull-right">
                    已有账号？<a href="${pageContext.request.contextPath}/login.html">登录</a>
                </div>
            </div>
            <div class="main">
                <div class="form-group  label-floating login-form-group">
                    <label for="name" class="control-label">用户名</label>
                    <input type="text" class="form-control" id="name" ng-model="user.nickname" autofocus>
                    <%--<span class="help-block">Please enter a valid email address</span>--%>
                </div>
                <div class="form-group  label-floating login-form-group">
                    <label for="email" class="control-label">邮箱</label>
                    <input type="text" class="form-control" id="email" ng-model="user.email" autofocus>
                    <%--<span class="help-block">Please enter a valid email address</span>--%>
                </div>
                <div class="form-group  label-floating login-form-group">
                    <label for="password" class="control-label">密码</label>
                    <input type="password" class="form-control" id="password" ng-model="user.password" autofocus>
                    <%--<span class="help-block">Please enter a valid email address</span>--%>
                </div>
                <div class="form-group  label-floating login-form-group">
                    <label for="rePassword" class="control-label">确认密码</label>
                    <input type="password" class="form-control" id="rePassword" ng-model="user.rePassword" autofocus>
                    <%--<span class="help-block">Please enter a valid email address</span>--%>
                </div>
                <%--<div class="row  form-horizontal form-group">--%>
                    <%--<div class="col-md-2">--%>
                        <%--<label class="control-label pull-right">用户名</label>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input type="text" ng-model="user.nickname" class="form-control" placeholder="" autofocus/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row  form-horizontal form-group">--%>
                    <%--<div class="col-md-2">--%>
                        <%--<label class="control-label pull-right">邮箱</label>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input type="text" ng-model="user.email" class="form-control" placeholder=""/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row  form-horizontal form-group">--%>
                    <%--<div class="col-md-2">--%>
                        <%--<label class="control-label pull-right">密码</label>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input type="password" ng-model="user.password" class="form-control">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="row  form-horizontal form-group">--%>
                    <%--<div class="col-md-2">--%>
                        <%--<label class="control-label pull-right">确认密码</label>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input type="password" ng-model="user.rePassword" class="form-control">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="tip">
                    <div class="alert alert-danger" ng-if="error">
                        <div ng-bind="error"></div>
                    </div>
                </div>
                <button type="submit" class="btn btn-raised btn-primary btn-lg btn-block" ng-click="register()" ng-if="!loading">注册</button>
                <button type="submit" class="btn btn-raised btn-primary btn-lg btn-block" ng-click="register()" ng-if="loading">
                    <i class="fa fa-refresh fa-spin"></i>注册中
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/registerService.js"></script>
<script>
    app.controller("registerController", function ($scope, registerService) {
        $scope.user = {
            nickname: '',
            email: '',
            password: '',
            rePassword: ''
        };
        $scope.loading=false;
        $scope.register = function () {
            if ($scope.user.nickname.length == 0) {
                $scope.error = "请入用户名";
                return;
            }
            if ($scope.user.nickname.length < 4 || $scope.user.nickname.length > 12) {
                $scope.error = "用户名长度必须为4——12个字符之间";
                return;
            }
            if ($scope.user.email.length == 0) {
                $scope.error = "请输入邮箱";
                return;
            }
            if (!Util.checkEmail($scope.user.email)) {
                $scope.error = "请输入正确的邮箱";
                return;
            }
            if ($scope.user.password.length == 0) {
                $scope.error = "请输入密码";
                return;
            }
            if ($scope.user.rePassword.length == 0) {
                $scope.error = "请输入确认密码";
                return;
            }
            if ($scope.user.password != $scope.user.rePassword) {
                $scope.error = "两次密码输入不一致";
                return;
            }
            if ($scope.user.password.length < 4 || $scope.user.password.length > 12) {
                $scope.error = "密码长度必须在4——12个字符之间";
                return;
            }
            $scope.loading=true;
            registerService.register($scope.user)
                    .success(function (response, status, headers, cfg) {
                        $scope.loading=false;
                        if (response.success) {
                            $scope.error = "";
                            alert("注册成功，请登录");
                            window.location.href="${pageContext.request.contextPath}/login.html";
                        } else {
                            $scope.error = response.msg;
                        }
                    }).error(function (response, status, headers, cfg) {
                $scope.loading=false;
                $scope.error = response;
            })
        }
    })
</script>


