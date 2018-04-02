app.service('focusArticleService', function ($http) {
    this.initList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/focusArticle/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }

    this.changeFocus = function (_id, _focus) {
        var req = {
            method: 'POST',
            url: context + '/user/focusArticle/changeFocus.rest?id=' + _id + '&focus=' + _focus,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
})