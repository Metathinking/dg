app.service('articleService', function ($http) {
    this.save = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/articleEdit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
    this.initList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/articleList.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
    this.getCategoryList = function () {
        var req = {
            method: 'GET',
            url: context + '/user/getCategoryList.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
    this.changeOpen = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/changeOpen.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        }
        return $http(req);
    }
    this.delete = function (_id) {
        var req = {
            method: 'GET',
            url: context + '/user/articleDelete.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
})