app.service('emailSettingEditService', function ($http) {
    var req = {
        method: 'POST',
        url: context + '/admin/emailSetting/edit.rest',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    this.edit = function (data) {
        req.data = data;
        return $http(req);
    }
})