app.service('emailSettingListService', function ($http) {

    this.getList = function (data) {
        var req = {
            method: 'POST',
            url: context + '/admin/emailSetting/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }
        return $http(req);
    }
})