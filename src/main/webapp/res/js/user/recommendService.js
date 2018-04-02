app.service("recommendService", function ($http) {
    this.edit = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/user/recommend/edit.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        };
        return $http(req);
    }
    this.getRecommend = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/user/recommend/findById.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }

    this.initList = function (_index, _size) {
        var param = "";
        if (_index != undefined) {
            param += "index=" + _index;
        }
        if (_size != undefined) {
            param += "&size=" + _size;
        }
        var req = {
            method: 'POST',
            url: context + '/user/recommend/list.rest?' + param,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return $http(req);
    }
    this.delete = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/user/recommend/delete.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json',
            }
        }
        return $http(req);
    }
})