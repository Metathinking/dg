app.service("suggestionService", function ($http) {
    this.submit = function (_data) {
        var req = {
            method: "POST",
            url: context + '/suggestion/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        }
        return $http(req);
    }
})