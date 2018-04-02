<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div ng-controller="articleListController" ng-init="findArticle()" class="content">
    <div class="row" style="margin-bottom: 40px">
        <div  class="col-md-3"></div>
        <div  class="col-md-7">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="" ng-model="keywords">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-raised btn-primary " ng-click="search()">搜索 </button>
                </div><!-- /btn-group -->
            </div><!-- /input-group -->
        </div>
        <div  class="col-md-3"></div>
    </div>

    <div class="row" >
        <div class="col-md-10 col-md-offset-1">
            <div class="well" ng-repeat="article in list" >
                <div class="media">
                    <a class="pull-left" href="${pageContext.request.contextPath}/article/{{article.id}}.html">
                        <img class="media-object" ng-src="{{article.image?article.image:'/res/img/null.jpg'}}" style="width: 150px;height: 150px;">
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
                            <%--<li>|</li>--%>
                            <%--<span> 2 comments</span>--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div home-pagination-directive page-list="pageList" link="/search.html" page-count="pageQuery.pageCount" index="index"></div>
</div>
<script>
    app.controller('articleListController',function($scope,$http){
        $scope.keywords="";
        $scope.search=function () {
            if($scope.keywords.length==0){
                return;
            }
            window.location.href="${pageContext.request.contextPath}/search.html?keywords="+$scope.keywords;
        };
        $scope.findArticle=function() {
            $scope.index = ${index};
            $scope.keywords = "${keywords}";
            var req = {
                method: 'POST',
                url: '${pageContext.request.contextPath}/article/list.rest',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    index:$scope.index,
                    keywords:$scope.keywords
                }
            }
            $http(req).success(function(response,status,headers,cfg){
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