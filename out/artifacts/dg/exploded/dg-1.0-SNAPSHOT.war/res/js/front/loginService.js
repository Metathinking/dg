app.service("loginService", function ($http) {
    this.login = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/login.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
});
