app.service("columnService", function ($http) {
    this.list = function () {
        var request = {
            method: "POST",
            url: context + "/admin/column/list.rest",
            headers: {
                "Content-Type": "application/json"
            }
        };
        return $http(request);
    };
    this.save = function (_data) {
        var request = {
            method: 'POST',
            url: context + '/admin/column/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(request);
    };
    this.delete = function (_id) {
        var request = {
            method: 'POST',
            url: context + '/admin/column/delete.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(request);
    }
})