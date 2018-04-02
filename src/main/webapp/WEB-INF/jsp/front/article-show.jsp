<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/12.0012
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="article-main" ng-controller="articleShowController" ng-init="initArticle(${id})">
    <%--<div class="row">--%>
    <div class="article-left">
        <div class="article">
            <h1>
                ${article.title}
            </h1>
            <div class="row ">
                <a class="label label-default" ng-repeat="item in tags" style="margin-left: 10px">{{item}}</a>
            </div>
            <ul class="list-unstyled list-inline text-right ">
                <li>
                    <fmt:formatDate value="${article.time}" pattern="yyyy-MM-dd"/>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/front/user/articleList/${article.userId}.html?"
                       target="_blank">
                        <%--<div ng-bind="article.userNickname"></div>--%>
                        ${article.userNickname}
                    </a>
                </li>
                <%--<li><i class="fa fa-eye fa-color"></i> 浏览(<span ng-bind="articleAboutInfo.browseCount"></span>)</li>--%>
                <%--<li><i class="fa fa-comment-o fa-color"></i>评论(<span ng-bind="articleAboutInfo.commentCount"></span>)</li>--%>
                <li><i class="fa fa-eye"></i> 浏览(<span ng-bind="articleAboutInfo.browseCount"></span>)</li>
                <li><i class="fa fa-comment-o"></i>评论(<span ng-bind="articleAboutInfo.commentCount"></span>)
                </li>
            </ul>


            <div class="article-content">
                <%--<div class="article-description" ng-if="article.description">--%>
                <div class="article-description">
                    <%--<span ng-bind="article.description"></span>--%>
                    ${article.description}
                </div>
                <div id="content">
                    ${article.content}
                </div>
            </div>
            <p style="font-size: 14px;color: #4952e9;margin: 24px">
                ${statement}
            </p>
            <%--<img src="${codeImageUrl}"/>--%>
            <ul class="share">
                <li>
                    <i class="weixin"  id="weixin" data-toggle="popover" data-placement="top" ></i>
                </li>
                <li>
                    <i class="weibo" title="微博分享"></i>
                </li>
            </ul>
            <ul class="list-unstyled list-inline blog-tags pull-right" style="display: none">
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
    <div class="article-right">
        <div ng-if="authorArticleList.length>0">
            <h4>作者文章</h4>
            <ul>
                <li ng-repeat="article in authorArticleList">
                    <a href="${pageContext.request.contextPath}/article/{{article.id}}.html">{{article.title}}</a>
                </li>
            </ul>
        </div>
        <div>
            <h4>热门文章</h4>
            <ul>
                <li ng-repeat="article in hotArticleList">
                    <a href="${pageContext.request.contextPath}/article/{{article.id}}.html">{{article.title}}</a>
                </li>
            </ul>
            <h4>最新文章</h4>
            <ul>
                <li ng-repeat="article in newArticleList">
                    <a href="${pageContext.request.contextPath}/article/{{article.id}}.html">{{article.title}}</a>
                </li>
            </ul>
            <%--<h3>我的广告</h3>--%>
            <script type="text/javascript">var jd_union_pid = "427549946";
            var jd_union_euid = "";</script>
            <script type="text/javascript" src="http://ads.union.jd.com/static/js/union.js"></script>
            <%--<ul >--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--<li><a href="http://www.baidu.com">作者 文章</a></li>--%>
            <%--</ul>--%>
            <script type="text/javascript">var jd_union_unid = "1000052468", jd_ad_ids = "513:6", jd_union_pid = "CMSykJzvKhD0re7cAxoAIOvhndEBKgA=";
            var jd_width = 300;
            var jd_height = 250;
            var jd_union_euid = "";
            var p = "BxAOUxNYEAEVNwpfBkgyTUMIRmtKRk9aZV8ETVxNNwpfBkgyDEcueFxzW2xlEUdafgNzHQYFLEILcgtZK14WBBYCXRNeFjISBlQaWhEBFwBcK2tKRk9aZVA1FDJNQwhGaxUHFARdHF4XABYPUBtrFDIiNw%3D%3D";</script>
            <script type="text/javascript" charset="utf-8" src="//u.x.jd.com/static/js/auto.js"></script>
        </div>
    </div>
    <%--</div>--%>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/getArticleService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/commentService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/focusArticleService.js"></script>
<script>
    app.controller('articleShowController', function ($scope, getArticleService, commentService, focusArticleService) {
        //获取文章信息 start
        $scope.initArticle = function (id) {
//            $scope.article = response.data.article;
//            $scope.tags = $scope.article.tag.split("|");
//            document.getElementById("content").innerHTML = $scope.article.content;
            $scope.tags = "${article.tag}".split("|");
            <%--document.getElementById("content").innerHTML = ${article.content};--%>
            getArticleService.findArticlePageMsg(id)
                    .success(function (response, status, headers, cfg) {
                        if (response.success) {

                            $scope.articleAboutInfo = response.data.articleAboutInfo;
                            $scope.focusArticle = response.data.focusArticle;
                            $scope.commentListVO = response.data.commentListVO;
                            $scope.own = response.data.own;
                            $scope.statement = response.data.statement;
                            $scope.authorArticleList = response.data.authorArticleList;
                            $scope.hotArticleList = response.data.hotArticleList;
                            $scope.newArticleList = response.data.newArticleList;

                        } else {

                        }
                    }).error(function (response, status, headers, cfg) {

            })
        }
        //获取文章信息 end
        //更改收藏状态 start
        $scope.changeFocus = function (_focus) {
            focusArticleService.changeFocus('${article.id}', _focus)
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
            getArticleService.changeEffect('${article.id}', _effect)
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
                articleId: '${article.id}',
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

    })
</script>
<script>
    $(function () {
        $("[data-toggle='popover']").popover();
    });

    $('#weixin').popover({
        trigger : 'hover',//鼠标以上时触发弹出提示框
        html:true,//开启html 为true的话，data-content里就能放html代码了
        content:"<img src='${codeImageUrl}'>"
    });
</script>