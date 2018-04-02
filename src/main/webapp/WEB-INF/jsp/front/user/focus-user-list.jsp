<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/7/9.0009
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="frontFocusUserController" ng-init="listFocusUserByUserId(${id},${index})">
    <div class="row">
        <div class="col-md-2 user-item" ng-repeat="userVO in list">
            <img ng-src="{{userVO.userMsg.icon?userVO.userMsg.icon:'/res/img/head.png'}}"
                 title="{{userVO.user.nickname}}"/>
            <br/>
            <a href="${pageContext.request.contextPath}/front/user/articleList/{{userVO.user.id}}.html" target="_blank">
                <div ng-bind="userVO.user.nickname"></div>
            </a>
            <br/>
        </div>
    </div>
    <div front-pagination-directive page-list="pageList" link="/front/user/focusUserList/${id}.html"
         page-count="pageQuery.pageCount" index="index"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/frontUserService.js"></script>
<script>
app.controller('frontFocusUserController',function($scope,frontUserService){

    $scope.userNav.action=2;
    $scope.listFocusUserByUserId = function(_id,_index){
        $scope.index=_index;
        frontUserService.listFocusUserByUserId(_id,_index)
                .success(function(response,status,headers,cfg){
                    if(response.success){
                        $scope.error="";
                        $scope.list = response.data.list;
                        $scope.pageQuery=response.data.pageQuery;
                        $scope.pageList=[];
                        for (var i=1;i<$scope.pageQuery.pageCount+1;i++){
                            $scope.pageList.push(i);
                        }
                    }else{
                        $scope.error=response.msg;
                    }

                }).error(function(response,status,headers,cfg){
            $scope.error="请求失败";
        })
    }
})
</script>
