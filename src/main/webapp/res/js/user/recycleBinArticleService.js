app.service('recycleBinArticleService', function ($http) {
    this.initList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/recycleBinArticle/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }

    this.delete = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/user/recycleBinArticle/delete.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }

    this.restore = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/user/recycleBinArticle/restore.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
})