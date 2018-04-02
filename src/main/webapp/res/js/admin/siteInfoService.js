app.service("siteInfoService", function ($http) {
    this.save = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/admin/siteInfo/edit.rest',
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
            url: context + '/admin/siteInfo/find.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
})