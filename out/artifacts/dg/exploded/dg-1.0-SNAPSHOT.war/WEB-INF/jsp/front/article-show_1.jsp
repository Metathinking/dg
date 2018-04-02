<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/12.0012
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container panel panel-default" ng-controller="articleShowController" ng-init="initArticle(${id})">
    <section id="wrapper">
        <div class="row">
            <div class="col-md-10">
                <div class="article">
                    <h3>
                        <div ng-bind="article.title"></div>
                    </h3>
                    <div class="row ">
                        <%--标签：<a ng-repeat="item in tags" href="#">{{item}}</a>--%>
                        <%--<a class="{{labelClass($index)}}" ng-repeat="item in tags" style="margin-left: 10px">{{item}}</a>--%>
                        <a class="label label-default" ng-repeat="item in tags" style="margin-left: 10px">{{item}}</a>
                    </div>
                    <ul class="list-unstyled list-inline text-right">
                        <li>
                            <div ng-bind="article.time|date:'yyyy-MM-dd HH:mm'"></div>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/front/user/articleList/{{article.userId}}.html?" target="_blank">
                                <div ng-bind="article.userNickname"></div>
                            </a>
                        </li>
                        <%--<li><i class="fa fa-eye fa-color"></i> 浏览(<span ng-bind="articleAboutInfo.browseCount"></span>)</li>--%>
                        <%--<li><i class="fa fa-comment-o fa-color"></i>评论(<span ng-bind="articleAboutInfo.commentCount"></span>)</li>--%>
                        <li><i class="fa fa-eye"></i> 浏览(<span ng-bind="articleAboutInfo.browseCount"></span>)</li>
                        <li><i class="fa fa-comment-o"></i>评论(<span ng-bind="articleAboutInfo.commentCount"></span>)
                        </li>
                    </ul>

                    <div class="article-content">
                        <div class="article-description" ng-if="article.description">
                            <span ng-bind="article.description"></span>
                        </div>
                        <div id="content"></div>
                    </div>
                    <%--<p style="font-size: 14px;color: #e96d5b;">--%>
                    <%--<div ng-bind="statement"></div>--%>
                    <%--</p>--%>
                    <p style="font-size: 14px;color: #4952e9;margin: 24px">
                        {{statement}}
                    </p>

                    <ul class="list-unstyled list-inline blog-tags pull-right">
                        <li>
                            <button class="btn btn-raised btn-sm btn-primary" ng-click="changeEffect(true)">
                                <span class="fa fa-hand-o-up"></span> 支持(<span
                                    ng-bind="articleAboutInfo.supportCount"></span>)
                            </button>
                        </li>
                        <li>
                            <button class="btn btn-raised btn-sm btn-success" ng-click="changeEffect(false)">
                                <span class="fa fa-hand-o-down"></span> 反对(<span
                                    ng-bind="articleAboutInfo.opposeCount"></span>)
                            </button>
                        </li>
                        <li ng-if="!own&&!focusArticle">
                            <button class="btn btn-raised btn-sm btn-danger" ng-click="changeFocus(true)">
                                <span class="fa fa-folder-open"> </span>收藏(<span
                                    ng-bind="articleAboutInfo.focusCount"></span>)
                            </button>
                        </li>
                        <li ng-if="!own&&focusArticle">
                            <button class="btn btn-raised btn-sm btn-default" ng-click="changeFocus(false)">
                                <span class="fa fa-folder-open-o"></span>取消收藏(<span
                                    ng-bind="articleAboutInfo.focusCount"></span>)
                            </button>
                        </li>
                    </ul>
                </div>
                <!--添加评论 start-->
                <div class="comment">
                    <div class="line"></div>
                    <textarea class="form-control " cols="50" ng-model="comment" placeholder="您可以在这里抒发一下您的想法"
                              rows="5"></textarea>
                    <button class="btn btn-raised btn-primary pull-right submit" ng-if="${USER!=null}"
                            ng-click="commentArticle()">马上发表
                    </button>
                    <button class="btn btn-raised btn-primary pull-right submit" ng-if="${USER==null}"
                            data-toggle="modal" data-target="#loginModal">登录后评论
                    </button>
                </div>
                <!--添加评论 end-->
                <!--评论列表 start-->
                <div class="commentList">
                    <div ng-if="commentListVO.length==0" class="alert alert-info">
                        该文章还没有任何评论，您现在就可以坐上沙发了
                    </div>
                    <div ng-if="commentListVO.length>0">
                        <ul class="chat">
                            <li class="clearfix" ng-repeat="commentVO in commentListVO">
                                <span class="chat-img pull-left">
                                    <img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar"
                                         class="img-circle"/>
                                </span>
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <strong>{{commentVO.comment.userNickname}}</strong>
                                        <small class="pull-right text-muted">
                                            <span class="glyphicon glyphicon-time"></span>{{commentVO.comment.time|date:'yyyy-MM-dd
                                            HH:mm:ss'}}
                                        </small>
                                    </div>
                                    <p>
                                        {{commentVO.comment.content}}
                                    </p>
                                    <div class="">
                                        <a class=" pull-right comment-option" ng-click="effectComment(commentVO,false)">踩<span
                                                class="">[{{commentVO.commentAboutInfo.opposeCount}}]</span> </a>
                                        <a class=" pull-right comment-option" ng-click="effectComment(commentVO,true)">顶<span
                                                class="">[{{commentVO.commentAboutInfo.supportCount}}]</span></a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div>

                </div>
            </div>
        </div>
    </section>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/getArticleService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/commentService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/focusArticleService.js"></script>
<script>
    app.controller('articleShowController', function ($scope, getArticleService, commentService, focusArticleService) {
        //获取文章信息 start
        $scope.initArticle = function (id) {
            getArticleService.findById(id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.article = response.data.article;
                            $scope.tags = $scope.article.tag.split("|");
                            document.getElementById("content").innerHTML = $scope.article.content;
                            $scope.articleAboutInfo = response.data.articleAboutInfo;
                            $scope.focusArticle = response.data.focusArticle;
                            $scope.commentListVO = response.data.commentListVO;
                            $scope.own = response.data.own;
                            $scope.statement = response.data.statement;
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //获取文章信息 end
        //更改收藏状态 start
        $scope.changeFocus = function (_focus) {
            focusArticleService.changeFocus($scope.article.id, _focus)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            if (response.data.changed) {
                                $scope.articleAboutInfo = response.data.articleAboutInfo;
                                $scope.focusArticle = response.data.focusArticle;
                            }
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //更改收藏状态 end
        //支持、反对 start
        $scope.changeEffect = function (_effect) {
            getArticleService.changeEffect($scope.article.id, _effect)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            if (response.data.changed) {
                                $scope.articleAboutInfo = response.data.articleAboutInfo;
                            }
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //支持、反对 end
        //评论文章 start
        $scope.commentArticle = function () {
            var data = {
                articleId: $scope.article.id,
                content: $scope.comment
            }
            commentService.commentArticle(data)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            $scope.commentListVO.unshift(response.data);
                            $scope.articleAboutInfo.commentCount = $scope.articleAboutInfo.commentCount + 1;
                            $scope.comment = "";
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //评论文章 end
        //支持、反对文章评论 start
        $scope.effectComment = function (commentVO, effect) {

            commentService.effectComment(commentVO.comment.id, effect)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {
                            commentVO.commentAboutInfo = response.data;
                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //支持、反对文章评论 end
        //标签样式 start
        $scope.labelClass = function (_index) {
            var number = _index % 5;
            switch (number) {
                case 0:
                    return "label label-primary";
                case 1:
                    return "label label-success";
                case 2:
                    return "label label-danger";
                case 3:
                    return "label label-info";
                case 4:
                    return "label label-warning";
                default:
                    return "label label-default";

            }
        }
        //标签样式 end
    })
</script>