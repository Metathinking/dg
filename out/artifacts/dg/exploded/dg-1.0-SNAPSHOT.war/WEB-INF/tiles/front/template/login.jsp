<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="headLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" ng-controller="loginController">
            <div class="modal-header" >
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin: 12px">&times;</button>
                <h3 class="modal-title">登录</h3>
            </div>
            <div class="modal-body">
                <div class="main">
                    <div class="form-group  label-floating login-form-group">
                        <label for="name" class="control-label">用户名/邮箱</label>
                        <input type="text" class="form-control" id="name" ng-model="user.name" autofocus>
                    </div>
                    <div class="form-group label-floating login-form-group">
                        <label for="password" class="control-label">密码</label>
                        <input type="password" class="form-control" id="password" ng-model="user.password">
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group label-floating login-form-group">
                                <label for="code" class="control-label">验证码</label>
                                <input type="text" class="form-control" id="code" ng-model="user.code">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <!--验证码 start-->
                            <img onClick="this.src=this.src+'?t='+Math.random()" id="imVcode" title="点击换一个校验码"
                                 src="${pageContext.request.contextPath}/code.rest" style="margin-top: 6px">
                            <!--验证码 end-->
                        </div>
                    </div>
                    <div class="tip">
                        <div class="alert alert-danger" ng-if="error">
                            <div ng-bind="error"></div>
                        </div>
                    </div>
                    <div class="checkbox pull-left">
                        <label style="padding-left: 0">
                            <input name="remember" type="checkbox" value="Remember Me"> 记住密码
                        </label>
                    </div>
                    <div class="pull-right">
                        <a class="btn btn-link" href="${pageContext.request.contextPath}/forgetPassword.html">忘记密码?</a>
                    </div>
                    <button class="btn btn-raised btn-primary btn-lg btn-block" ng-click="login()" ng-show="!loading">
                        登录
                    </button>
                    <button type="button" class="btn btn-raised btn-primary btn-lg btn-block" ng-if="loading">
                        <i class="fa fa-refresh fa-spin"></i>登录中
                    </button>
                </div>
            </div>
            <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/loginService.js"></script>
            <script>
                app.controller("loginController", function ($scope, loginService) {
                    $scope.error = "";
                    $scope.user = {
                        name: '',
                        password: '',
                        code: ''
                    }
                    $scope.loading = false;
                    $scope.login = function () {
                        if ($scope.user.name.length == 0) {
                            $scope.error = "请入用户名或邮箱";
                            return;
                        }
                        if ($scope.user.password.length == 0) {
                            $scope.error = "请输入密码";
                            return;
                        }
                        if ($scope.user.code.length == 0) {
                            $scope.error = "请输入验证码";
                            return;
                        }
                        $scope.loading = true;
                        var data = {
                            nickname: $scope.user.name,
                            password: hex_md5($scope.user.password),
                            code:$scope.user.code
                        }
                        loginService.login(data).success(function (response, status, headers, cfg) {
                            $scope.loading = false;
                            if (response.success) {
                                $scope.error = "";
                                $scope.user = {
                                    name:'',
                                    password:'',
                                    code:''
                                }
                                window.location.reload();
                            } else {
                                $scope.error = response.msg;
                            }
                        }).error(function (response, status, headers, cfg) {
                            $scope.loading = false;
                            $scope.error = response;
                        })
                    }
                })
            </script>
            <script>
                $(function () {
                    $('#loginModal').on('shown.bs.modal', function () {
                        $("#navbar").css("padding-right","17px")
                    })
                });
                $(function () {
                    $('#loginModal').on('hidden.bs.modal', function () {
                        $("#navbar").css("padding-right","0")
                    })
                });
            </script>
        </div>
    </div>
</div>
