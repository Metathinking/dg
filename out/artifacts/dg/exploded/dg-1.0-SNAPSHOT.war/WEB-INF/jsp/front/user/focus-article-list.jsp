<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/7/6.0006
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="frontFocusArticleController" ng-init="listArticleByUserId(${id},${index})">
    <div class="row" >
        <div class="col-md-10 col-md-offset-1">
            <div class="well" ng-repeat="article in list" >
                <div class="media">
                    <a class="pull-left" href="${pageContext.request.contextPath}/article/{{article.id}}.html">
                        <img class="media-object" ng-src="{{article.image?article.image:'http://placekitten.com/150/150'}}" style="width: 150px;height: 150px;">
                    </a>
                    <div class="media-body">
                        <h4 class="media-heading"><a href="${pageContext.request.contextPath}/article/{{article.id}}.html"><div ng-bind="article.title"></div></a> </h4>
                        <p><div ng-bind="article.description"></div></p>
                        <ul class="list-inline list-unstyled">
                            <li>
                                <a href="${pageContext.request.contextPath}/front/user/articleList/{{article.userId}}.html" target="_blank">
                                    <div ng-bind="article.userNickname"></div>
                                </a>
                            </li>
                            <li>|</li>
                            <li><div ng-bind="article.time|date:'yyyy-MM-dd HH:mm'"></div></li>
                            <li>|</li>
                            <span> 2 comments</span>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div front-pagination-directive page-list="pageList" link="/front/user/focusArticleList/${id}.html" page-count="pageQuery.pageCount" index="index"></div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/frontUserService.js"></script>
<script>
    app.controller("frontFocusArticleController",function($scope,frontUserService){

        $scope.userNav.action=3;
        $scope.listArticleByUserId = function(_id,_index){
            $scope.index=_index;
            frontUserService.listFocusArticleByUserId(_id,_index)
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