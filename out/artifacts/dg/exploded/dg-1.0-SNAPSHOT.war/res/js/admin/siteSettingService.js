app.service("siteSettingService", function ($http) {
    this.save = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/admin/siteSetting/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        }
        return $http(req);
    }
    this.find = function () {
        var req = {
            method: 'POST',
            url: context + '/admin/siteSetting/find.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
})