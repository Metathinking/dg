app.service('articleService', function ($http) {
    this.initList = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/admin/articleManager/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        }
        return $http(req);
    }
})