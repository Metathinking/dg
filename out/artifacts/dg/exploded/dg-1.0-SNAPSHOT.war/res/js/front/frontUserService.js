app.service("frontUserService", function ($http) {
    this.findUserById = function (_id) {
        var request = {
            method: 'POST',
            url: context + '/front/user/findById/' + _id + '.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(request);
    };
    this.listArticleByUserId = function (_id, _index) {
        var request = {
            method: 'POST',
            url: context + '/front/user/articleList/' + _id + '.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(request);
    }
    this.listFocusArticleByUserId = function (_id, _index) {
        var request = {
            method: 'POST',
            url: context + '/front/user/focusArticleList/' + _id + '.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(request);
    };
    this.listFocusUserByUserId = function (_id, _index) {
        var request = {
            method: 'POST',
            url: context + '/front/user/focusUserList/' + _id + '.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(request);
    };
    this.listFansByUserId = function (_id, _index) {
        var request = {
            method: 'POST',
            url: context + '/front/user/fansList/' + _id + '.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(request);
    };
})