app.service('emailRecordListService', function ($http) {
    this.getList = function (data) {
        var req = {
            method: 'POST',
            url: context + '/admin/email/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }
        return $http(req);
    }
})