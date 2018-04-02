app.service("commentService", function ($http) {
    this.commentArticle = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/commentArticle.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
    this.effectComment = function (_commentId, _effect) {
        var req = {
            method: 'POST',
            url: context + '/user/effectComment.rest?id=' + _commentId + '&effect=' + _effect,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
})