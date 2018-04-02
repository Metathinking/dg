app.service('templateListService', function ($http) {
    this.getList = function (data) {
        var req = {
            method: 'POST',
            url: context + '/admin/emailMsgTemplate/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }
        return $http(req);
    }
    this.setDefault = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/admin/emailMsgTemplate/setDefault.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            },
        }
        return $http(req);
    }
})