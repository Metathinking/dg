<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/17.0017
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="" ng-controller="articleEditController" >
    <div class="article-content">
        <div class="form-group" ng-init="getCategoryList()">
            <label>分类</label>
            <div class="row">
                <div class="col-md-2">
                    <select class="form-control" ng-model="article.category"
                            ng-options="c.name as c.description for c in categoryList">
                        <option value="">请选择</option>
                    </select>
                </div>
                <div class="col-md-10">
                    <input type="text" class="form-control" ng-model="article.originalUrl"
                           ng-readonly="article.category!='REPRINT'"
                           placeholder="原文地址，只在转载时有效"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>标题</label>
            <input type="text" class="form-control" ng-model="article.title"/>
        </div>
        <div class="form-group">
            <label>文章内容</label>
            <textarea id="editor_id" name="content" style="width:100%;height:600px;" >
                ${article==null?"":article.content}
            </textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-raised btn-primary" data-toggle="modal" data-target="#linkModal" ng-click="selectLinkModelType('add')">添加超链接</button>
        </div>
        <div class="form-group">
            <label>标题图片</label>
            <input type="text" class="form-control" ng-model="article.image"/>
        </div>
        <div class="form-group">
            <button class="btn btn-raised btn-primary" data-toggle="modal" data-target="#recommendModal" ng-click="selectLinkModelType('select')">图片跳转链接</button>
            <div ng-if="imageLink" style="-ms-word-wrap: break-word;word-wrap: break-word;word-break: break-all;overflow: hidden;">
                {{imageLink.description}}:{{imageLink.recommendUrl}}
            </div>

        </div>
        <!--图片上传 start-->
        <div class="form-group ">
            <label>添加图片</label>(1、图片大小不能超过2M；2、支持格式：.jpg .gif .png .bmp)
            <div class="panel panel-default" ng-controller="imageController">
                <div class="panel-body" nv-file-drop="" uploader="imageController.uploader"
                     filters="queueLimit, customFilter">
                    <input type="file" nv-file-select="" uploader="imageController.uploader"/>
                    <ol>
                        <li ng-repeat="item in imageController.images">{{item}}
                            <button class="btn btn-raised btn-sm btn-primary"  data-toggle="modal" data-target="#imgPropModal" ng-click="selectImage(item)">添加
                            </button>
                            <button class="btn btn-raised btn-sm btn-success" ng-click="selectTitleImage(item)">设为标题图片
                            </button>
                        </li>
                    </ol>
                    <div class="alert alert-danger" ng-if="imageError">
                        {{imageError}}
                    </div>
                    <table class="table" ng-if="imageController.uploader.queue.length>0">
                        <thead>
                        <tr>
                            <th width="50%">图片</th>
                            <th ng-show="imageController.uploader.isHTML5">大小</th>
                            <th ng-show="imageController.uploader.isHTML5">进度</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in imageController.uploader.queue">
                            <td><strong>{{ item.file.name }}</strong></td>
                            <td ng-show="imageController.uploader.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2}} MB
                            </td>
                            <td ng-show="imageController.uploader.isHTML5">
                                <div class="progress" style="margin-bottom: 0;">
                                    <div class="progress-bar" role="progressbar"
                                         ng-style="{ 'width': item.progress + '%' }"></div>
                                </div>
                            </td>
                            <td class="text-center">
                                <span ng-show="item.isSuccess">成功</span>
                                <span ng-show="item.isError">失败</span>
                            </td>
                            <td nowrap>
                                <button type="button" class="btn btn-raised btn-success btn-xs" ng-click="item.upload()"
                                        ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                    上传
                                </button>
                                <button type="button" class="btn btn-raised btn-danger btn-xs" ng-click="item.remove()">
                                    移除
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!--图片上传 end-->
        <div class="form-group">
            <label>标签</label>（添加Tag，你的内容能被更多人看到,请用“|”作为分隔符，例如：互联网|华为|任正非）
            <input type="text" class="form-control" ng-model="article.tag"/>
        </div>
        <div class="form-group">
            <label>简介</label>（请在这里添加文章的简单介绍）
            <textarea style="width: 100%;height: 100px" ng-model="article.description"></textarea>
        </div>

        <div class="alert alert-danger" ng-if="error">
            {{error}}
        </div>
        <div class="form-group text-center">
            <button class="btn btn-raised btn-primary" ng-click="save(true)">发表文章</button>
            <button class="btn btn-raised btn-primary" style="margin-left: 40px;margin-right: 40px"
                    ng-click="save(false)">立即保存
            </button>
            <button class="btn btn-raised btn-default" ng-click="reset()">舍弃</button>
        </div>
    </div>
    <!--modal start-->
    <div class="modal fade" id="linkModal" tabindex="-1" role="dialog" aria-labelledby="headLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">添加超链接</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" class="form-control" ng-model="link.name" autofocus placeholder="超链接提示">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" ng-model="link.title" autofocus placeholder="title属性">
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" ng-model="link.url" placeholder="超链接地址"
                                  style="height: 200px"></textarea>
                        <p class="help-block">超链接地址需要以：http://或https://开始</p>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-raised btn-primary" data-toggle="modal" data-target="#recommendModal">
                            选择推广链接
                        </button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" data-dismiss="modal" ng-click="addLink()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!--modal end-->
    <!--recommendModal start-->
    <div class="modal fade" id="recommendModal" tabindex="-1" role="dialog" aria-labelledby="headLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">我的推广</h4>
                </div>
                <div class="modal-body" ng-init="initRecommendList(${index})">
                    <div ng-if="list.length>0">
                        <table class="table table-striped table-hover"
                               style="table-layout: fixed;word-break: break-all">
                            <tr>
                                <td>序号</td>
                                <td>说明</td>
                                <td>图片</td>
                                <td>原地址</td>
                                <td>推荐地址</td>
                                <td>时间</td>
                                <td>操作</td>
                            </tr>
                            <tr ng-repeat="item in list">
                                <td>{{item.id}}</td>
                                <td>{{item.description}}</td>
                                <td>
                                    <a href="{{item.image}}" target="_blank">
                                        <img ng-src="{{item.image}}" width="100"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="{{item.shopUrl}}" target="_blank">
                                        {{item.shopUrl}}
                                    </a>
                                </td>
                                <td>
                                    <a href="{{item.recommendUrl}}" target="_blank">
                                        {{item.recommendUrl}}
                                    </a>
                                </td>
                                <td>{{item.time|date:'yyyy-MM-dd HH:mm:ss'}}</td>
                                <td>
                                    <button class="btn btn-raised btn-primary btn-sm btn-option" ng-click="selectRecommend(item)" data-dismiss="modal">选择</button>
                                </td>
                            </tr>
                        </table>
                        <%--<div front-pagination-directive page-list="pageList" link="/user/recommend/list.html"--%>
                             <%--page-count="pageQuery.pageCount" index="index"></div>--%>
                        <div admin-pagination-directive page-list="pageList" init-list="initRecommendList"
                             page-count="pageQuery.pageCount" index="index"></div>
                    </div>
                    <div class="alert alert-info" ng-if="list.length==0">
                        你还没有添加任何推广
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    <!--recommendModal end-->
    <!--imgPropModal start-->
    <div class="modal fade" id="imgPropModal" tabindex="-1" role="dialog" aria-labelledby="headLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">图片属性设置</h4>
                </div>
                <div class="modal-body">
                    <img ng-src="{{image.imageSrc}}" style="max-width: 200px;max-height: 200px" class="center-block"/>
                    <div class="form-group">
                        <input type="text" class="form-control" ng-model="image.title" autofocus placeholder="title属性">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" ng-model="image.alt" placeholder="alt属性">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" data-dismiss="modal"  ng-click="addImageToContent()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!--imgPropModal end-->
</div>
<script charset="utf-8" src="${pageContext.request.contextPath}/res/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/res/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/res/js/controller.js"></script>
<script>
    KindEditor.ready(function (K) {
        window.editor = K.create('#editor_id', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            allowImageUpload: false,
            filterMode:false,
//            cssDate:'{font: 18px/32px "Microsoft Yahei";color: #404040;text-indent: 2em;margin: 32px 0 0;}',
            items: [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons']
        });
    });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/articleService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/front/getArticleService.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/user/recommendService.js"></script>
<script>
    app.controller("articleEditController",
            function ($scope, articleService, getArticleService, recommendService) {
                $scope.nav.index = 4;
                $scope.rightNav.index = 2;
                $scope.error = "";
                if("${id}"=="-1"){
                    $scope.article = {
                        title: "",
                        category: "",
                        originalUrl: "",
                        image: "",
                        tag: "",
                        description: ""
                    };
                }else{
                    $scope.article = {
                        id:'${article.id}',
                        title: "${article.title}",
                        category: "${article.category}",
                        originalUrl: "${article.originalUrl}",
                        image: "${article.image}",
                        tag: "${article.tag}",
                        description: "${article.description}",
                    };
                }
                $scope.imageController = {
                    images: [],
                    uploader: {},
                    useWaterLogo : true
                };
                $scope.link={
                    name:"",
                    url:""
                };
                //图片链接
                $scope.imageLink=null;
                $scope.linkModelType="";
                $scope.image ={};
                $scope.selectImage = function(_imageSrc){
                    $scope.image.imageSrc = _imageSrc;
                };
                $scope.addImageToContent = function () {
                    window.editor.sync();
                    var image;
                    if($scope.imageLink!=null){
                        image = "<a href='"+$scope.imageLink.recommendUrl +"' target='_blank'> <div align='center'> <img src='" + $scope.image.imageSrc + "' style='width:80%' title='"+
                                $scope.image.title+"' alt='"+$scope.image.alt+"'/></div></a>";
                    }else{
                        image = "<div align='center'> <img src='" + $scope.image.imageSrc + "' style='width:80%' title='"+$scope.image.title+"' alt='"+$scope.image.alt+"'/></div>";
                    }

                    window.editor.insertHtml(image);
                }

                $scope.selectTitleImage = function (_imageSrc) {
                    $scope.article.image = _imageSrc;
                }
                //保存文章 start
                $scope.save = function (isOpen) {
                    if ($scope.article.title.length == 0) {
                        $scope.error = "请填写文章标题";
                        return;
                    }
                    if ($scope.article.title.length < 6 || $scope.article.title.length > 128) {
                        $scope.error = "文章标题长度为6——128个字符之间";
                        return;
                    }
                    if ($scope.article.category.length == 0) {
                        $scope.error = "请选择文章分类";
                        return;
                    }
                    if ($scope.article.category == "REPRINT" && $scope.article.originalUrl.length == 0) {
                        $scope.error = "您的文章为转载，请填写原文地址";
                        return;
                    }
                    window.editor.sync();
                    $scope.article.content = document.getElementById('editor_id').value;
                    if ($scope.article.content.length == 0) {
                        $scope.error = "请填写文章内容";
                        return;
                    }
                    if ($scope.article.image.length == 0) {
                        $scope.error = "请上传并选择文章的标题图片";
                        return;
                    }
                    if ($scope.article.tag.length == 0) {
                        $scope.error = "请填写文章标签，将被用于搜索引擎的搜索使用";
                        return;
                    }
                    if ($scope.article.description.length == 0) {
                        $scope.error = "请填写文章的简介，可在文章中摘录一段文字";
                        return;
                    }
                    $scope.article.open = isOpen;
                    articleService.save($scope.article)
                            .success(function (response, status, headers, cfg) {
                                if (response.success) {
                                    $scope.error = "";
                                    alert(response.msg);
                                    $scope.reset();
                                } else {
                                    $scope.error = response.msg;
                                }
                            }).error(function (response, status, headers, cfg) {
                        $scope.error = response;
                    })
                }
                //保存文章 end
                $scope.reset = function () {
                    $scope.error = "";
                    $scope.article = {};
                    $scope.imageController.images = [];
                    $scope.imageController.uploader.queue = [];
                    window.editor.html('');
                }
                $scope.getCategoryList = function () {
                    articleService.getCategoryList()
                            .success(function (response, status, headers, cfg) {
                                if (response.success) {
                                    $scope.categoryList = response.data;
                                } else {
                                    $scope.error = response.msg;
                                }
                            }).error(function (response, status, headers, cfg) {
                        $scope.error = response;
                    })
                }

                //初始化推广链接 start
                $scope.initRecommendList = function (_index) {
                    $scope.index = _index;
                    recommendService.initList(_index)
                            .success(function (response, status, headers, cfg) {
                                if (response.success) {
                                    $scope.list = response.data.list;
                                    $scope.pageQuery = response.data.pageQuery;
                                    $scope.pageList = [];
                                    for (var i = 1; i < $scope.pageQuery.pageCount + 1; i++) {
                                        $scope.pageList.push(i);
                                    }
                                } else {
                                    $scope.error = response.msg;
                                }
                            }).error(function (response, status, headers, cfg) {
                        $scope.error = response;
                    })
                }
                //初始化推广链接 end
                //选择推广 start
                $scope.selectRecommend = function(_recommend){
                    if($scope.linkModelType=='add'){
                        $scope.link.url=_recommend.recommendUrl;
                    }else{
                        $scope.imageLink = _recommend;
                    }

                }
                //选择推广 end

                //添加超链接 start
                $scope.addLink = function(){
                    if($scope.link.name.length==0){
                        return;
                    }
                    if($scope.link.url.length==0){
                        return;
                    }
                    window.editor.sync();
                    var link = "<a href='"+$scope.link.url+"' target='_blank' title='"+$scope.link.title+"' style='text-decoration: none'>"+$scope.link.name+" </a>";
                    window.editor.insertHtml(link);
                    $scope.link.name="";
                    $scope.link.url="";
                    $scope.link.title="";
                }
                //添加超链接 end
                //超链接模态框类型
                $scope.selectLinkModelType = function(_type){
                    $scope.linkModelType = _type
                }
            })
</script>