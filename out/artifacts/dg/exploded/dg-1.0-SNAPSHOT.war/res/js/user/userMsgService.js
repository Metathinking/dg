app.service('userMsgService', function ($http) {
    this.find = function () {
        var request = {
            method: 'POST',
            url: context + '/user/userMsg/find.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(request);
    };

    this.save = function (_data) {
        var request = {
            method: 'POST',
            url: context + '/user/userMsg/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(request);
    }
})