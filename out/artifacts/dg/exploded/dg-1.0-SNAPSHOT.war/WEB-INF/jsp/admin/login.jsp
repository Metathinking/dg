<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div  ng-controller="adminLoginController">
        <div class="passport-wrapper ">
            <div class="text-center" style="margin-bottom: 12px">
                <a href="${pageContext.request.contextPath}/">
                    <img class="head-logo" src="${pageContext.request.contextPath}/res/img/logo.png"/>
                </a>
            </div>
            <div class="passport-sign">
                <div class="row">
                    <h3 class="heading-desc text-center" style="display:block">登录</h3>
                </div>
                <div class="main">
                    <div class="form-group  label-floating login-form-group">
                        <label for="name" class="control-label">账号</label>
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

                    <button class="btn btn-raised btn-primary btn-lg btn-block" ng-click="login()" ng-show="!loading">
                        登录
                    </button>
                    <button type="button" class="btn btn-raised btn-primary btn-lg btn-block" ng-if="loading">
                        <i class="fa fa-refresh fa-spin"></i>登录中
                    </button>
                </div>
                <span class="clearfix"></span>
            </div>
        </div>
    <%--<form class="form-signin mg-btm">--%>
        <%--<h3 class="heading-desc">登录</h3>--%>
        <%--<div class="main">--%>
            <%--<label class="pull-left">账号</label>--%>
            <%--<div class="form-group">--%>
                <%--<input type="text" ng-model="user.name" class="form-control" placeholder="账号" autofocus="autofocus"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"/>--%>
            <%--</div>--%>
            <%--<label class="pull-left">密码</label>--%>
            <%--<div class="form-group">--%>
                <%--<input type="password" ng-model="user.password" class="form-control">--%>
            <%--</div>--%>
            <%--<button type="submit" class="btn btn-success btn-lg btn-block" ng-click="login()">登录--%>
            <%--</button>--%>
        <%--</div>--%>
        <%--<span class="clearfix"></span>--%>
        <%--<div class="alert alert-danger" ng-if="error">--%>
            <%--<div ng-bind="error"></div>--%>
        <%--</div>--%>
    <%--</form>--%>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/adminLoginService.js"></script>
<script>
    app.controller("adminLoginController", function ($scope, adminLoginService) {
        $scope.user = {
            name: '',
            password: '',
            code: ''
        }
        $scope.loading = false;
        $scope.login = function () {
            if ($scope.user.name.length == 0) {
                $scope.error = "请入账号";
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
                id: $scope.user.name,
                password: hex_md5($scope.user.password),
                code:$scope.user.code
            }
            adminLoginService.login(data).success(function (response, status, headers, cfg) {
                $scope.loading = false;
                if (response.success) {
                    $scope.error = "";
                    window.location.href="${pageContext.request.contextPath}/admin/home.html";
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

