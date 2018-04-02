app.service('userManagerService', function ($http) {
    this.initList = function (_index) {
        var req = {
            method: 'POST',
            url: context + '/admin/userManager/list.rest',
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