app.service('loginLogService', function ($http) {
    this.initList = function (_index) {
        var req = {
            method: 'POST',
            url: context + '/admin/loginLog/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        }
        return $http(req);
    }
})