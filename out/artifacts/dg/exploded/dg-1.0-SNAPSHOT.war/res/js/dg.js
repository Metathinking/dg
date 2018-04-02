'use strict';
var app = angular.module("app", ['angularFileUpload']);

app.service("paginationService", function () {
    var showPageCount = 9;
    var middlePage = parseInt(showPageCount / 2 + 1);
    var frontPageCount = parseInt(showPageCount / 2);
    this.getStart = function (_index, _pageCount) {
        if (_pageCount < showPageCount) {
            return 0;
        }
        if (_index < middlePage) {
            return 0;
        }
        if (_index + frontPageCount >= _pageCount) {
            return _pageCount - showPageCount;
        }
        return _index - middlePage;
    };
    this.getEnd = function (_index, _pageCount) {
        if (_pageCount < showPageCount) {
            return _pageCount;
        }
        if (_index < middlePage) {
            return showPageCount;
        }
        return _index + frontPageCount;
    };
});
app.directive("homePaginationDirective", function () {
    return {
        restrict: 'A',
        replace: true,
        scope: {
            pageList: '=',
            link: '@',
            index: '=',
            pageCount: '='
        },
        templateUrl: context + '/res/html/home-pagination.html',
        controller: ['$scope', 'paginationService', function ($scope, paginationService) {
            $scope.getStart = function (_index, _pageCount) {
                return paginationService.getStart(_index, _pageCount);
            }
            $scope.getEnd = function (_index, _pageCount) {
                return paginationService.getEnd(_index, _pageCount);
            }

        }]
    }
})
app.directive("frontPaginationDirective", function () {
    return {
        restrict: 'A',
        replace: true,
        scope: {
            pageList: '=',
            link: '@',
            index: '=',
            pageCount: '='
        },
        templateUrl: context + '/res/html/front-pagination.html',
        controller: ['$scope', 'paginationService', function ($scope, paginationService) {
            $scope.getStart = function (_index, _pageCount) {
                return paginationService.getStart(_index, _pageCount);
            }
            $scope.getEnd = function (_index, _pageCount) {
                return paginationService.getEnd(_index, _pageCount);
            }

        }]
    }
})
app.directive("adminPaginationDirective", function () {
    return {
        restrict: 'A',
        replace: true,
        scope: {
            pageList: '=',//对象绑定
            initList: '=',//对象绑定
            index: '=',//存储于index相关的字符串
            pageCount: '='
        },

        templateUrl: context + '/res/html/admin-pagination.html',
        controller: ['$scope', 'paginationService', function ($scope, paginationService) {
            $scope.getStart = function (_index, _pageCount) {
                return paginationService.getStart(_index, _pageCount);
            }
            $scope.getEnd = function (_index, _pageCount) {
                return paginationService.getEnd(_index, _pageCount);
            }

        }]
    }
})
