<%--
  Created by IntelliJ IDEA.
  User: luyilaosan1
  Date: 2016/6/15.0015
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div ng-controller="columnController" ng-init="list()" >
    <div class="">
        <table class="table" >
            <tr>
                <td>栏目名称</td>
                <td>关键词</td>
                <td>栏目地址</td>
                <td>排序</td>
                <td>操作</td>
            </tr>
            <tr ng-repeat="column in list">
                <td>
                    <input type="text" ng-model="column.name"/>
                </td>
                <td>
                    <input type="text" ng-model="column.keywords"/>
                </td>
                <td>
                    <input type="text"  ng-model="column.url" style="width: 500px" readonly/>
                </td>
                <td>
                    <input type="text"  ng-model="column.order" style="width: 50px"/>
                </td>
                <td>
                    <button class="btn btn-raised btn-danger btn-sm btn-option" ng-click="delete(column.id)" ><span class="glyphicon glyphicon-trash"></span></button>
                </td>
            </tr>
        </table>
    </div>
    <div ng-if="error" class="alert alert-danger">
        {{error}}
    </div>
    <div class="form-group text-center">
        <button class="btn btn-raised btn-primary" ng-click="save()">保存</button>
        <button class="btn btn-raised btn-default" ng-click="addColumn()">添加栏目</button>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/admin/columnService.js"></script>
<script>
    app.controller('columnController',function($scope,columnService){
        $scope.nav.index=5;
        $scope.leftNav.index=4;
        $scope.list=[];
        //获取 网站设置信息 start
        $scope.list = function(){
            backOption(columnService.list());
        }
        //获取网站设置信息 end

        //保存 start
        $scope.save = function(){
            backOption(columnService.save($scope.list));
        }
        //保存 end
        $scope.delete = function(_id){
            backOption(columnService.delete(_id));

        };

        $scope.addColumn = function(){
            $scope.list.push({
                id:0,
                name:'',
                keywords:'',
                url:'',
                order:''
            })
        }

        //处理返回信息
        var backOption= function(back){
            back .success(function(response,status,headers,cfg){
                if(response.success){
                    $scope.error="";
                    $scope.list = response.data;
                    if($scope.list.length<5){
                        var listLength = $scope.list.length;
                        for(var i=0;i<5-listLength;i++)
                        $scope.list.push({
                            id:0,
                            name:'',
                            keywords:'',
                            url:'',
                            order:''
                        })
                    }
                }else{
                    $scope.error=response.msg;
                }
            }).error(function(response,status,headers,cfg){})
        }
    })
</script>
