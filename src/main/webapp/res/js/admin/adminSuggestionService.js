app.service("adminSuggestionService", function ($http) {
    this.initList = function (_index) {
        var req = {
            method: 'POST',
            url: context + '/admin/suggestion/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(req);
    };
    this.handle = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/admin/suggestion/handle.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    };
    this.delete = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/admin/suggestion/delete.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
})