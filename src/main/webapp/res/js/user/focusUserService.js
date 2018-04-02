app.service("focusUserService", function ($http) {
    this.getFocusUserList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/focusUser/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    };

    this.changeFocus = function (_id, _focus) {
        var req = {
            method: 'POST',
            url: context + '/user/focusUser/changeFocus.rest?id=' + _id + '&focus=' + _focus,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    };

    this.getFansList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/fans/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
})