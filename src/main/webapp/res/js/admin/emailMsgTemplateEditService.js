app.service('emailMsgTemplateEditService', function ($http) {
    this.getTemplateTypes = function () {
        var req = {
            method: 'POST',
            url: context + '/admin/emailMsgTemplate/getTypes.rest',
            headers: {
                'Content-Type': 'application/json'
            },
        }
        return $http(req);
    }
    this.findById = function (id) {
        var req = {
            method: 'POST',
            url: context + '/admin/emailMsgTemplate/findById.rest?id=' + id,
            headers: {
                'Content-Type': 'application/json'
            },
        }
        return $http(req);
    }
    this.save = function (data) {
        var req = {
            method: 'POST',
            url: context + '/admin/emailMsgTemplate/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }
        return $http(req);
    }
})