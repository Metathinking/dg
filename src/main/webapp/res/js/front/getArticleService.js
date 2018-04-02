app.service('getArticleService', function ($http) {
    this.findArticlePageMsg = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/article/findArticlePageMsg.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }


    this.changeEffect = function (_id, _effect) {
        var req = {
            method: 'POST',
            url: context + '/user/article/changeEffect.rest?id=' + _id + '&effect=' + _effect,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
})