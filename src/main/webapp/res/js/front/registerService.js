app.service("registerService", function ($http) {
    this.register = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/register.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req)
    }
})